package jh.naverwebtoon.db.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDate;
import jh.naverwebtoon.db.domain.enums.CountryResidence;
import jh.naverwebtoon.db.domain.enums.Gender;
import jh.naverwebtoon.dto.request.EditMemberReq;
import jh.naverwebtoon.dto.request.JoinMemberReq;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "login_id")})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProfileImage profileImage;

    public static Member createMember(JoinMemberReq joinMemberReq) {
        Member member = new Member();
        member.loginId = joinMemberReq.getLoginId();
        member.password = joinMemberReq.getPassword();
        member.emailAddress = joinMemberReq.getEmailAddress();
        member.name = joinMemberReq.getName();
        member.birthDate = joinMemberReq.getBirthDate();
        member.gender = joinMemberReq.getGender();
        member.countryResidence = joinMemberReq.getCountryResidence();
        member.phoneNumber = joinMemberReq.getPhoneNumber();
        return member;
    }

    public void changeProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public void edit(EditMemberReq editMemberReq) {
        if (editMemberReq.getUserName() != null) {
            this.name = editMemberReq.getUserName();
        }
        if (editMemberReq.getPassword() != null) {
            this.password = editMemberReq.getPassword();
        }
        if (editMemberReq.getEmailAddress() != null) {
            this.emailAddress = editMemberReq.getEmailAddress();
        }
        if (editMemberReq.getBirthDate() != null) {
            this.birthDate = editMemberReq.getBirthDate();
        }
        if (editMemberReq.getGender() != null) {
            this.gender = editMemberReq.getGender();
        }
        if (editMemberReq.getCountryResidence() != null) {
            this.countryResidence = editMemberReq.getCountryResidence();
        }
        if (editMemberReq.getPhoneNumber() != null) {
            this.phoneNumber = editMemberReq.getPhoneNumber();
        }
    }


}
