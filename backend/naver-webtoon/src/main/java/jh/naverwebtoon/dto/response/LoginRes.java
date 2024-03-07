package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginRes {
    private Long id;
    private String loginId;
    private String userName;
}
