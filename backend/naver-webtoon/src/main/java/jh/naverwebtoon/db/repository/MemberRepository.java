package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.ProfileImage;
import jh.naverwebtoon.db.domain.UploadImage;
import jh.naverwebtoon.db.domain.enums.CountryResidence;
import jh.naverwebtoon.db.domain.enums.Gender;
import jh.naverwebtoon.dto.request.JoinMemberReq;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initMember() {
        JoinMemberReq joinMemberReq = new JoinMemberReq();
        joinMemberReq.setLoginId("joohee56");
        joinMemberReq.setPassword("1234");
        joinMemberReq.setEmailAddress("joohee@naver.com");
        joinMemberReq.setName("이주희");
        joinMemberReq.setBirthDate(LocalDate.of(1999, 9, 19));
        joinMemberReq.setGender(Gender.FEMALE);
        joinMemberReq.setCountryResidence(CountryResidence.LOCAL);
        joinMemberReq.setPhoneNumber("010-1234-5678");
        Member member = Member.createMember(joinMemberReq);
        ProfileImage profileImage = ProfileImage.createProfileImage(new UploadImage("수달.jpeg", "ae738f79-ec30-40c9-ace9-c369ccf076c2.jpeg"));
        member.changeProfileImage(profileImage);
        em.persist(member);
    }

    public Long save(Member m) {
        em.persist(m);
        return m.getId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findByLoginId(String loginId) {
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
    }
}
