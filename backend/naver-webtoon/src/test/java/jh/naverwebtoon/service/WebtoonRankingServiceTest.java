package jh.naverwebtoon.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import jh.naverwebtoon.dto.response.FindWebtoonRankingRes;
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
        List<FindWebtoonRankingRes> rankings = service.findRanking();
        for (FindWebtoonRankingRes ranking : rankings) {
            System.out.println(ranking.toString());
        }
    }

}