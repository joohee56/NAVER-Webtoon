package jh.naverwebtoon.db.repository;

import java.util.List;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.response.FindNewRanking;
import jh.naverwebtoon.web.controller.SocketController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WebtoonRankingRepositoryTest {
    @Autowired
    private WebtoonRankingRepository repository;
    @Autowired
    private SocketController socketController;

//    @Test
//    void 이전_랭킹_조회() {
//        int offset = 0, limit = 10;
//        WebtoonType webtoonType = WebtoonType.OFFICIAL;
//        List<WebtoonRanking> rankings = repository.findLatestRankings(offset, limit, webtoonType);
//        for (WebtoonRanking ranking : rankings) {
//            System.out.println(ranking.getWebtoon().getId() + ", " + ranking.getWebtoon().getName() + ", " + ranking.getWebtoon().getWebtoonThumbnail().getPosterImage().getStoreFileName() + ", " + ranking.getTotalLikeCount() + ", " + ranking.getRanking());
//            for (WebtoonGenre webtoonGenre : ranking.getWebtoon().getGenres()) {
//                System.out.println(webtoonGenre.getGenre().getTitle());
//            }
//        }
//    }

    @Test
    void 새_랭킹_조회() {
        int offset=0, limit = 10;
        WebtoonType webtoonType = WebtoonType.OFFICIAL;
        List<FindNewRanking> findNewRankings = repository.findRankingsByRecentRounds(offset, limit, webtoonType);
        for (FindNewRanking findNewRanking : findNewRankings) {
            System.out.println(findNewRanking.getWebtoonId() + "," + findNewRanking.getTotalLikeCount());
        }
    }
}