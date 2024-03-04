package jh.naverwebtoon.db.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import jh.naverwebtoon.db.domain.enums.CountryResidence;
import jh.naverwebtoon.db.domain.enums.Gender;
import jh.naverwebtoon.dto.request.MemberJoinReq;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "login_id"), @UniqueConstraint(columnNames = "email_address")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String emailAddress;

    @Column(name="member_name", nullable = false)
    private String name;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private CountryResidence countryResidence;

    private String phoneNumber;

    public static Member createMember(MemberJoinReq memberJoinReq) {
        Member member = new Member();
        member.loginId = memberJoinReq.getLoginId();
        member.password = memberJoinReq.getPassword();
        member.emailAddress = memberJoinReq.getEmailAddress();
        member.name = memberJoinReq.getName();
        member.birthDate = memberJoinReq.getBirthDate();
        member.gender = memberJoinReq.getGender();
        member.countryResidence = memberJoinReq.getCountryResidence();
        member.phoneNumber = memberJoinReq.getPhoneNumber();
        return member;
    }
}
