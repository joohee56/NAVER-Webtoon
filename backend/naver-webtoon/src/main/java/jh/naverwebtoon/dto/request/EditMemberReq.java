package jh.naverwebtoon.dto.request;

import java.time.LocalDate;
import jh.naverwebtoon.db.domain.enums.CountryResidence;
import jh.naverwebtoon.db.domain.enums.Gender;
import lombok.Data;

@Data
public class EditMemberReq {
    private String userName;
    private String password;
    private String emailAddress;
    private LocalDate birthDate;
    private Gender gender;
    private CountryResidence countryResidence;
    private String phoneNumber;
}
