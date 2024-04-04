package jh.naverwebtoon.db.repository;

import java.util.List;
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
    void test() {
        List webtoons = repository.findRankingsByRecentRounds();
        for(Object webtoon : webtoons) {
            Object[] field = (Object[]) webtoon;

        }
    }

}