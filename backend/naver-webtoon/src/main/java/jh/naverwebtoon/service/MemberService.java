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
        Member member = Member.createMember(memberJoinReq);
        System.out.println("====MemberJoinReq 출력: ==== " + memberJoinReq.getName());
        System.out.println("====Name 출력: ==== " + member.getName());
        return memberRepository.save(member);
    }

}
