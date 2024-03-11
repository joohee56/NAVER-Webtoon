package jh.naverwebtoon.dto.response;

import java.time.LocalDate;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.enums.CountryResidence;
import jh.naverwebtoon.db.domain.enums.Gender;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfoRes {
    private String userName;
    private String password;
    private String emailAddress;
    private LocalDate birthDate;
    private Gender gender;
    private CountryResidence countryResidence;
    private String phoneNumber;

    public static MemberInfoRes create(Member member) {
        MemberInfoRes memberInfoRes = new MemberInfoRes();
        memberInfoRes.userName = member.getName();
        memberInfoRes.password = member.getPassword();
        memberInfoRes.emailAddress = member.getEmailAddress();
        memberInfoRes.birthDate = member.getBirthDate();
        memberInfoRes.gender = member.getGender();
        memberInfoRes.countryResidence = member.getCountryResidence();
        memberInfoRes.phoneNumber = member.getPhoneNumber();
        return memberInfoRes;
    }
}
