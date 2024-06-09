package jh.naverwebtoon.db.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WebtoonRankingSetRepositoryTest {
    @Autowired
    private WebtoonRankingSetRepository repository;

    @Test
    void 새_랭킹_집합_생성() {
        System.out.println(repository.save().getId());
    }

    @Test
    void 가장_최근_랭킹_집합_조회() {
        Long setId = repository.findLatestOne();
        System.out.println(setId);
    }
}