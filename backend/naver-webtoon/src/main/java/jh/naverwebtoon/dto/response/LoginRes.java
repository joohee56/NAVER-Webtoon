package jh.naverwebtoon.dto.response;

import jh.naverwebtoon.db.domain.Member;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRes {
    private String loginId;
    private String userName;
    private String profileImage;

    public static LoginRes create(Member member) {
        LoginRes loginRes = new LoginRes();
        loginRes.loginId = member.getLoginId();
        loginRes.userName = member.getName();
        if (member.getProfileImage() != null) {
            loginRes.profileImage = member.getProfileImage().getUploadImage().getStoreFileName();
        }
        return loginRes;
    }
}
