package jh.naverwebtoon.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import jh.naverwebtoon.db.domain.enums.CountryResidence;
import jh.naverwebtoon.db.domain.enums.Gender;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class JoinMemberReq {
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    @Email
    private String emailAddress;
    @NotBlank
    private String name;
    @NotNull
    private LocalDate birthDate;
    @NotNull
    private Gender gender;
    @NotNull
    private CountryResidence countryResidence;
    @NotBlank
    private String phoneNumber;
}
