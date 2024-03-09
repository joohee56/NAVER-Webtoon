package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.List;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.enums.CountryResidence;
import jh.naverwebtoon.db.domain.enums.Gender;
import jh.naverwebtoon.dto.request.MemberJoinReq;
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
        MemberJoinReq memberJoinReq = new MemberJoinReq();
        memberJoinReq.setLoginId("joohee56");
        memberJoinReq.setPassword("1234");
        memberJoinReq.setEmailAddress("joohee@naver.com");
        memberJoinReq.setName("이주희");
        memberJoinReq.setBirthDate(LocalDate.of(1999, 9, 19));
        memberJoinReq.setGender(Gender.FEMALE);
        memberJoinReq.setCountryResidence(CountryResidence.LOCAL);
        memberJoinReq.setPhoneNumber("010-1234-5678");
        Member member = Member.createMember(memberJoinReq);
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
