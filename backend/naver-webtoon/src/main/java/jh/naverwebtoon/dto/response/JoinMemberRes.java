package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinMemberRes {
    private String loginId;
    private String name;
}
