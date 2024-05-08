package jh.naverwebtoon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.WebtoonRanking;
import jh.naverwebtoon.db.domain.enums.RankingStatus;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.db.repository.WebtoonRankingRepository;
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

    /**
     * 가장 최근 웹툰 랭킹 조회
     */
    public List<WebtoonRankingDto> findRanking(int offset, int limit, WebtoonType webtoonType) {
        return  webtoonRankingRepository.findLatestOne(offset, limit, webtoonType).stream()
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
        //1. 웹툰들의 최근 10회차 중 좋아요 누적 수가 가장 높은 14개의 웹툰 리스트 조회
        List<FindNewRanking> findNewRankings = webtoonRankingRepository.findRankingsByRecentRounds(offset, limit, webtoonType);

        //2. 상승, 하강, 변화없음 비교를 위해 이전 웹툰 랭킹 조회
        List<WebtoonRanking> latestRankings = webtoonRankingRepository.findLatestOne(offset, limit, webtoonType);

        //3. 새 랭킹 저장
        List<WebtoonRanking> newRankings = new ArrayList<>();
        int ranking = 1, idx = 0;
        while(idx < findNewRankings.size()) {
            //전의 랭킹과 동점이라면 같은 랭킹, 아니라면 랭킹 증가 (낮은 랭킹)
            if (idx>=1 && findNewRankings.get(idx-1).getTotalLikeCount() != findNewRankings.get(idx).getTotalLikeCount()) {
                ranking++;
            }

            Long webtoonId = findNewRankings.get(idx).getWebtoonId();
            Long totalLikeCount = findNewRankings.get(idx).getTotalLikeCount();

            //랭킹 상승, 하강, 변함없음 계산
            RankingStatus status = calculateStatus(webtoonId, ranking-1, latestRankings);
            Webtoon webtoon = webtoonRepository.findOneWithMember(webtoonId);
            newRankings.add(WebtoonRanking.create(webtoon, ranking, status, totalLikeCount));

            idx++;
        }

        //웹툰 랭킹이 낮은 순서대로 정렬 (랭킹이 낮은 순서 먼저 저장 -> 조회시 랭킹이 높은 순서대로 조회)
        newRankings.sort((w1, w2) -> Integer.compare(w2.getRanking().ordinal(), w1.getRanking().ordinal()));
        for (WebtoonRanking newRanking : newRankings) {
            webtoonRankingRepository.save(newRanking);
        }

        //4. 최신 랭킹 조회
        return findRanking(0, 7, webtoonType);
    }

    public RankingStatus calculateStatus(Long webtoonId, int ranking, List<WebtoonRanking> latestRankings) {
        if (latestRankings.isEmpty()) {
            return RankingStatus.UP;
        }

        for(WebtoonRanking latestRanking : latestRankings) {
            if (latestRanking.getWebtoon().getId() == webtoonId) {
                if(latestRanking.getRanking().ordinal() < ranking) {
                    return RankingStatus.DOWN;
                } else if(latestRanking.getRanking().ordinal() > ranking) {
                    return RankingStatus.UP;
                } else {
                    return RankingStatus.UNCHANGING;
                }
            }
        }
        return RankingStatus.UP;
    }
}
