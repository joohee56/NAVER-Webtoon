package jh.naverwebtoon.web.controller;

import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.dto.request.MemberJoinReq;
import jh.naverwebtoon.dto.response.MemberJoinRes;
import jh.naverwebtoon.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users/")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public MemberJoinRes join(@ModelAttribute MemberJoinReq memberJoinReq) {
        System.out.println("====service MemberJoinReq: =====" + memberJoinReq.getName());
        Long savedId = memberService.join(memberJoinReq);
        Member member = memberRepository.findOne(savedId);
        return new MemberJoinRes(member.getLoginId());
    }

}
