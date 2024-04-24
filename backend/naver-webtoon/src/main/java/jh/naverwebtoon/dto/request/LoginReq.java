package jh.naverwebtoon.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class LoginReq {
    @NotBlank
    String loginId;
    @NotBlank
    String password;
}
