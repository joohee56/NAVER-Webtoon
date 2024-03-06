package jh.naverwebtoon.service;

import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.repository.MemberRepository;
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
}
