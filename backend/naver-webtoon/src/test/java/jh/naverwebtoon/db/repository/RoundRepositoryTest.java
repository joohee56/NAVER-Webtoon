package jh.naverwebtoon.db.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import jh.naverwebtoon.dto.response.FindRoundsManageRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RoundRepositoryTest {
    @Autowired
    private RoundRepository roundRepository;

    @Test
    void 회차_관리_정보_조회() {
        List<FindRoundsManageRes> rounds = roundRepository.findAllByWebtoonWithManage(Long.valueOf(2));
        for (FindRoundsManageRes round : rounds) {
            System.out.println(round.toString());
        }
    }
}