package jh.naverwebtoon.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.dto.request.EditMemberReq;
import jh.naverwebtoon.dto.request.JoinMemberReq;
import jh.naverwebtoon.dto.request.LoginReq;
import jh.naverwebtoon.dto.response.JoinMemberRes;
import jh.naverwebtoon.dto.response.LoginRes;
import jh.naverwebtoon.dto.response.MemberInfoRes;
import jh.naverwebtoon.service.MemberService;
import jh.naverwebtoon.util.FileStore;
import jh.naverwebtoon.web.Login;
import jh.naverwebtoon.web.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final FileStore fileStore;

    /**
     * 회원가입
     */
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/join", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public JoinMemberRes join(@ModelAttribute JoinMemberReq joinMemberReq) {
        Long savedId = memberService.join(joinMemberReq);
        Member member = memberRepository.findOne(savedId);
        return new JoinMemberRes(member.getLoginId(), member.getName());
    }

    /**
     * 아이디 중복 검사
     */
    @GetMapping("/duplicated/{loginId}")
    public Boolean isDuplicatedLoginId(@PathVariable("loginId") String loginId) {
        return memberService.duplicatedLoginId(loginId);
    }

    /**
     * 로그인
     */
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public LoginRes login(@ModelAttribute LoginReq loginReq, HttpServletRequest request) {
        Member loginMember = memberService.login(loginReq);

        //세션 설정
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER_ID, loginMember.getId());
        session.setMaxInactiveInterval(60*30); //세션 유효 기간 30분

        return LoginRes.create(loginMember);
    }

    /**
     * 로그아웃
     */
    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        session.invalidate();
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

    /**
     * 프로필 이미지 변경
     */
    @PostMapping(value = "/profileImage", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String changeProfileImage(@Login Long id, @RequestPart(value="profileImage") MultipartFile profileImage) throws IOException {
        String storeFileName = memberService.changeProfileImage(id, profileImage);
        return storeFileName;
    }

    /**
     * 사용자 정보 변경
     */
    @PutMapping("/edit")
    public MemberInfoRes editMember(@Login Long id, @RequestBody EditMemberReq editMemberReq) {
        Member editMember = memberService.editMemberInfo(id, editMemberReq);
        return MemberInfoRes.create(editMember);
    }
}
