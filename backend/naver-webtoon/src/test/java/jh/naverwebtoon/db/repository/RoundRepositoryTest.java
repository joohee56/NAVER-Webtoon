package jh.naverwebtoon.db.repository;

import java.util.List;
import jh.naverwebtoon.dto.response.FindRoundsManageRes;
import jh.naverwebtoon.dto.response.RoundListDto;
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
        int offset = 0, limit = 10;
        List<FindRoundsManageRes> rounds = roundRepository.findAllByWebtoonWithManage(Long.valueOf(2), offset, limit);
        for (FindRoundsManageRes round : rounds) {
            System.out.println(round.toString());
        }
    }

    @Test
    void 회차_리스트_조회() {
        List<RoundListDto> rounds = roundRepository.findAllByWebtoonWithPaging(Long.valueOf(2), 0, 15,
                true);
        System.out.println(rounds.isEmpty());
        for (RoundListDto round : rounds) {
            System.out.println(round.toString());
        }
    }
}