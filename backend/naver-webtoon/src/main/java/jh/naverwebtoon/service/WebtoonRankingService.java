package jh.naverwebtoon.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.WebtoonRanking;
import jh.naverwebtoon.db.domain.enums.RankingStatus;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.db.repository.WebtoonGenreRepository;
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
    private final WebtoonGenreRepository webtoonGenreRepository;
    private final WebtoonRepository webtoonRepository;


    /**
     * 가장 최근 웹툰 랭킹 조회
     */
    public List<WebtoonRankingDto> findRanking(int offset, int limit) {
        return webtoonRankingRepository.findLatestOne(offset, limit).stream()
                .map(webtoonRanking -> WebtoonRankingDto.create(webtoonRanking))
                .collect(Collectors.toList());
    }

    /**
     * 웹툰 랭킹 갱신 및 조회(설정된 시간에 자동으로 호출, 웹 소켓으로 전송)
     */
    @Transactional
    public List<WebtoonRankingDto> updateRanking(int offset, int limit) {
        //1. 웹툰들의 최근 10회차 중 좋아요 누적 수가 가장 높은 10개의 웹툰 리스트 조회
        List<FindNewRanking> findNewRankings = webtoonRankingRepository.findRankingsByRecentRounds();

        //2. 상승, 하강, 변함없음 비교를 위해 이전 웹툰 랭킹 조회
        List<WebtoonRanking> latestRankings = webtoonRankingRepository.findLatestOne(offset, limit);

        List<WebtoonRankingDto> webtoonRankings = new ArrayList<>();
        for(int ranking=findNewRankings.size(); ranking >=1; ranking--) {  //최신순으로 조회했을 때, 낮은 순위가 먼저 저장되어야 높은 순위부터 조회된다.
            FindNewRanking findNewRanking = findNewRankings.get(ranking-1);

            RankingStatus status = calculateStatus(findNewRanking.getWebtoonId(), ranking-1, latestRankings);
            List<Genre> genres = webtoonGenreRepository.findGenreByWebtoonId(findNewRanking.getWebtoonId());
            if(ranking <= 5) {  //5개만 반환
                webtoonRankings.add(WebtoonRankingDto.create(findNewRanking, status, genres, ranking));
            }
            Webtoon webtoon = webtoonRepository.findOne(findNewRanking.getWebtoonId());

            //3. 현재 랭킹 저장
            WebtoonRanking webtoonRanking = WebtoonRanking.create(webtoon, ranking, status,
                    findNewRanking.getTotalLikeCount());
            webtoonRankingRepository.save(webtoonRanking);
        }

        //웹툰 순위가 높은 순서대로 정렬
        webtoonRankings.sort(Comparator.comparingInt(w -> w.getRankingNum()));
        return webtoonRankings;
    }

    public RankingStatus calculateStatus(Long webtoonId, int ranking, List<WebtoonRanking> latestRankings) {
        if (latestRankings.isEmpty()) {
            return RankingStatus.UP;
        } else {
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
}
