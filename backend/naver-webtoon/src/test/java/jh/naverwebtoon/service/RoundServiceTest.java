package jh.naverwebtoon.service;

import jh.naverwebtoon.dto.request.CreateRoundReq;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RoundServiceTest {
    @Autowired
    private RoundService roundService;

    @Test
    void 회차_저장_중_잘못된_회원아이디_접근() {
        Long memberId = Long.valueOf(1);
        CreateRoundReq createRoundReq = new CreateRoundReq(Long.valueOf(2), "", null, null, null, "");
        Assertions.assertThatThrownBy(()->roundService.save(memberId, createRoundReq))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("잘못된 접근입니다.");
    }
}