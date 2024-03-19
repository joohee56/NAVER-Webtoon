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
    public void init() {
        initMember("joohee56", "1234", "joohee@naver.com", "이주희", LocalDate.of(1999, 9, 19), Gender.FEMALE, CountryResidence.LOCAL, "010-1234-5678", "jjanggu-profile-image.png" );
        initMember("author1", "1234", "author1@naver.com", "정이나", LocalDate.of(1999, 9, 19), Gender.FEMALE, CountryResidence.LOCAL, "010-1234-5678", "작가_프로필_1.png" );
        initMember("author2", "1234", "author2@naver.com", "홍덕", LocalDate.of(1999, 9, 19), Gender.FEMALE, CountryResidence.LOCAL, "010-1234-5678", null);
        initMember("author3", "1234", "author3@naver.com", "모랑지", LocalDate.of(1999, 9, 19), Gender.FEMALE, CountryResidence.LOCAL, "010-1234-5678", "작가_프로필_2.png");
        initMember("challenger1", "1234", "challenger1@naver.com", "딱지 간호사", LocalDate.of(1999, 9, 19), Gender.FEMALE, CountryResidence.LOCAL, "010-1234-5678", "도전만화_프로필_1.png");
    }

    public void initMember(String loginId, String password, String emailAddress, String name, LocalDate birthDate, Gender gender, CountryResidence countryResidence, String phoneNumber, String storeFileName) {
        JoinMemberReq joinMemberReq = new JoinMemberReq();
        joinMemberReq.setLoginId(loginId);
        joinMemberReq.setPassword(password);
        joinMemberReq.setEmailAddress(emailAddress);
        joinMemberReq.setName(name);
        joinMemberReq.setBirthDate(birthDate);
        joinMemberReq.setGender(gender);
        joinMemberReq.setCountryResidence(countryResidence);
        joinMemberReq.setPhoneNumber(phoneNumber);
        Member member = Member.createMember(joinMemberReq);

        ProfileImage profileImage = ProfileImage.createProfileImage(new UploadImage(storeFileName, storeFileName));
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
