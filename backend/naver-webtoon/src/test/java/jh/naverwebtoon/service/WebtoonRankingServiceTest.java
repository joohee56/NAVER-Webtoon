package jh.naverwebtoon.service;

import java.util.List;
import jh.naverwebtoon.dto.response.WebtoonRankingDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WebtoonRankingServiceTest {
    @Autowired
    private WebtoonRankingService service;

    @Test
    void test() {
        List<WebtoonRankingDto> rankings = service.findRanking();
        for (WebtoonRankingDto ranking : rankings) {
            System.out.println(ranking.toString());
        }
    }

    @Test
    void 웹툰_랭킹_갱신_및_조회() {
        System.out.println("첫 번째");
        List<WebtoonRankingDto> webtoons = service.updateRanking();

        for (WebtoonRankingDto webtoon : webtoons) {
            System.out.println(webtoon.toString());
        }

        System.out.println("두 번째");
        List<WebtoonRankingDto> webtoons2 = service.updateRanking();

        for (WebtoonRankingDto webtoon : webtoons2) {
            System.out.println(webtoon.toString());
        }
    }

}