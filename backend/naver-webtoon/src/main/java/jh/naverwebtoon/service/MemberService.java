package jh.naverwebtoon.service;

import java.util.List;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.dto.request.LoginReq;
import jh.naverwebtoon.dto.request.MemberJoinReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(MemberJoinReq memberJoinReq) {
        //LoginId 중복 검사
        if(duplicatedLoginId(memberJoinReq.getLoginId())) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다.");
        }

        Member member = Member.createMember(memberJoinReq);
        return memberRepository.save(member);
    }

    public Boolean duplicatedLoginId(String loginId) {
        return !memberRepository.findByLoginId(loginId).isEmpty();
    }

    public Member login(LoginReq loginReq) {
        List<Member> findMembers = memberRepository.findByLoginId(loginReq.getLoginId());
        if (findMembers.isEmpty()) {
            throw new IllegalArgumentException("가입되지 않은 아이디입니다.");
        }
        if (!findMembers.get(0).getPassword().equals(loginReq.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return findMembers.get(0);
    }
}
