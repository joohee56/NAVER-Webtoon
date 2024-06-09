package jh.naverwebtoon.service;

import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.WebtoonRanking;
import jh.naverwebtoon.db.domain.WebtoonRankingSet;
import jh.naverwebtoon.db.domain.enums.RankingStatus;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.db.repository.WebtoonRankingRepository;
import jh.naverwebtoon.db.repository.WebtoonRankingSetRepository;
import jh.naverwebtoon.db.repository.WebtoonRepository;
import jh.naverwebtoon.dto.response.FindNewRanking;
import jh.naverwebtoon.dto.response.WebtoonRankingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WebtoonRankingService {
    private final WebtoonRankingRepository webtoonRankingRepository;
    private final WebtoonRepository webtoonRepository;
    private final WebtoonRankingSetRepository webtoonRankingSetRepository;

    /**
     * 최근 웹툰 랭킹 조회
     */
    public List<WebtoonRankingDto> findRanking(int offset, int limit, WebtoonType webtoonType) {
        Long rankingSetId = webtoonRankingSetRepository.findLatestOne(webtoonType);
        return  webtoonRankingRepository.findLatestRankings(offset, limit, rankingSetId, webtoonType).stream()
                .map(webtoonRanking -> WebtoonRankingDto.create(webtoonRanking))
                .collect(Collectors.toList());
    }

    /**
     * 웹툰 랭킹 갱신 및 조회
     * - 지정된 시간에 자동으로 호출
     * - 웹 소켓으로 전송
     */
    @Transactional
    public List<WebtoonRankingDto> updateRanking(int offset, int limit, WebtoonType webtoonType) {
        //1. 새로운 랭킹 조회
        List<FindNewRanking> findNewRankings = webtoonRankingRepository.findRankingsByRecentRounds(offset, limit, webtoonType);

        //2. 이전 랭킹 조회 (상승, 하강, 변동없음 비교)
        Long rankingSetId = webtoonRankingSetRepository.findLatestOne(webtoonType);  //최신 랭킹 집합 아이디
        List<WebtoonRanking> latestRankings = webtoonRankingRepository.findLatestRankings(offset, limit, rankingSetId, webtoonType);

        //3. 새 랭킹 db 저장
        int rankingNo = 1, idx = 0;
        WebtoonRankingSet webtoonRankingSet = webtoonRankingSetRepository.save(webtoonType);  //새 랭킹 집합 아이디 생성
        while(idx < limit) {
            //전의 랭킹과 동점이라면 같은 랭킹번호, 아니라면 랭킹번호 증가
            if (idx>=1 && findNewRankings.get(idx-1).getTotalLikeCount() != findNewRankings.get(idx).getTotalLikeCount()) {
                rankingNo = idx+1;
            }

            Long webtoonId = findNewRankings.get(idx).getWebtoonId();
            Long totalLikeCount = findNewRankings.get(idx).getTotalLikeCount();
            RankingStatus status = calculateStatus(webtoonId, rankingNo, latestRankings);
            Webtoon webtoon = webtoonRepository.findOneWithMember(webtoonId);
            webtoonRankingRepository.save(WebtoonRanking.create(webtoon, rankingNo, status, totalLikeCount, webtoonRankingSet));
            idx++;
        }

        //3. 최신 랭킹 조회
        return findRanking(offset, limit/2, webtoonType);
    }

    /**
     * 웹툰 랭킹의 상승, 하강, 변함없음 계산
     */
    public RankingStatus calculateStatus(Long webtoonId, int rankingNo, List<WebtoonRanking> latestRankings) {
        if (latestRankings.isEmpty()) {
            return RankingStatus.UP;
        }

        for(WebtoonRanking latestRanking : latestRankings) {
            if (latestRanking.getWebtoon().getId().equals(webtoonId)) {
                if (latestRanking.getRankingNo() < rankingNo) {  //이전의 숫자보다 크다면 -> 하강 (ex) 1->3)
                    return RankingStatus.DOWN;
                } else if (latestRanking.getRankingNo() > rankingNo) {  //이전의 숫자보다 작다면 -> 상승 (ex) 4->1)
                    return RankingStatus.UP;
                } else {
                    return RankingStatus.UNCHANGING;
                }
            }
        }
        return RankingStatus.UP;
    }
}
