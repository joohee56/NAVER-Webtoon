package jh.naverwebtoon.service;

import java.util.List;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.ProfileImage;
import jh.naverwebtoon.db.domain.UploadImage;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.dto.request.EditMemberReq;
import jh.naverwebtoon.dto.request.JoinMemberReq;
import jh.naverwebtoon.dto.request.LoginReq;
import jh.naverwebtoon.util.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final FileStore fileStore;

    /**
     * 회원가입
     */
    @Transactional
    public Long join(JoinMemberReq joinMemberReq) {
        //LoginId 중복 검사
        if(duplicatedLoginId(joinMemberReq.getLoginId())) {
            throw new IllegalArgumentException("이미 가입된 아이디입니다.");
        }

        Member member = Member.createMember(joinMemberReq);
        return memberRepository.save(member);
    }

    public Boolean duplicatedLoginId(String loginId) {
        return !memberRepository.findByLoginId(loginId).isEmpty();
    }

    /**
     * 로그인
     */
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

    /**
     * 프로필 이미지 변경
     */
    @Transactional
    public String changeProfileImage(Long id, MultipartFile profileImage) {
        Member member = memberRepository.findOne(id);
        if (member.getProfileImage() != null) {
            String storeFileName = member.getProfileImage().getUploadImage().getStoreFileName();
            fileStore.deleteFile(storeFileName);  //기존의 프로필 이미지 삭제
        }
        UploadImage uploadImage = fileStore.storeFile(profileImage);
        member.changeProfileImage(ProfileImage.createProfileImage(uploadImage));
        return member.getProfileImage().getUploadImage().getStoreFileName();
    }

    /**
     * 사용자 정보 수정
     */
    @Transactional
    public Member editMemberInfo(Long id, EditMemberReq editMemberReq) {
        Member findMember = memberRepository.findOne(id);
        findMember.edit(editMemberReq);
        return findMember;
    }
}
