package jh.naverwebtoon.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.dto.request.LoginReq;
import jh.naverwebtoon.dto.request.MemberJoinReq;
import jh.naverwebtoon.dto.response.LoginRes;
import jh.naverwebtoon.dto.response.MemberInfoRes;
import jh.naverwebtoon.dto.response.MemberJoinRes;
import jh.naverwebtoon.service.MemberService;
import jh.naverwebtoon.web.Login;
import jh.naverwebtoon.web.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public MemberJoinRes join(@ModelAttribute MemberJoinReq memberJoinReq) {
        Long savedId = memberService.join(memberJoinReq);
        Member member = memberRepository.findOne(savedId);
        return new MemberJoinRes(member.getLoginId(), member.getName());
    }

    @GetMapping("/duplicated/{loginId}")
    public Boolean isDuplicatedLoginId(@PathVariable("loginId") String loginId) {
        return memberService.duplicatedLoginId(loginId);
    }

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public LoginRes login(@ModelAttribute LoginReq loginReq, HttpServletRequest request) {
        Member loginMember = memberService.login(loginReq);

        //세션 설정
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER_ID, loginMember.getId());
        session.setMaxInactiveInterval(60*30); //세션 유효 기간 30분

        return new LoginRes(loginMember.getLoginId(), loginMember.getName());
    }

    /**
     * 회원 정보 조회
     */
    @GetMapping()
    public MemberInfoRes MemberInfo(@Login Long id) {
        System.out.println("memberInfo 호출");
        System.out.println("id = " + id);
        Member findMember = memberRepository.findOne(id);
        return MemberInfoRes.create(findMember);
    }

}
