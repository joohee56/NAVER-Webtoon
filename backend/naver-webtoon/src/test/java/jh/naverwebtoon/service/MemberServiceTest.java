package jh.naverwebtoon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.dto.request.JoinMemberReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void 회원가입() {
        //given
        JoinMemberReq joinMemberReq = new JoinMemberReq();
        joinMemberReq.setLoginId("joohee56");
        joinMemberReq.setPassword("1234");
        joinMemberReq.setName("이주희");

        //when
        Long savedId = memberService.join(joinMemberReq);

        //then
        assertEquals(savedId, memberRepository.findOne(savedId).getId());
    }

}