package jh.naverwebtoon.db.repository;

import java.util.List;
import jh.naverwebtoon.db.domain.WebtoonGenre;
import jh.naverwebtoon.db.domain.WebtoonRanking;
import jh.naverwebtoon.dto.response.FindNewRanking;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WebtoonRankingRepositoryTest {
    @Autowired
    private WebtoonRankingRepository repository;

    @Test
    void 이전_랭킹_조회() {
        List<WebtoonRanking> rankings = repository.findLatestOne();
        for (WebtoonRanking ranking : rankings) {
            System.out.println(ranking.getWebtoon().getId() + ", " + ranking.getWebtoon().getName() + ", " + ranking.getWebtoon().getWebtoonThumbnail().getPosterImage().getStoreFileName() + ", " + ranking.getTotalLikeCount() + ", " + ranking.getRanking());
            for (WebtoonGenre webtoonGenre : ranking.getWebtoon().getGenres()) {
                System.out.println(webtoonGenre.getGenre().getGenreEnum().getTitle());
            }
        }
    }

    @Test
    void 최신_랭킹_조회() {
        List<FindNewRanking> findNewRankings = repository.findRankingsByRecentRounds();
        for (FindNewRanking findNewRanking : findNewRankings) {
            System.out.println(findNewRanking.getWebtoonId() + ", " + findNewRanking.getWebtoonName() + ", " + findNewRanking.getThumbnail().getPosterImage().getStoreFileName());
        }
    }
}