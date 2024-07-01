package jh.naverwebtoon.db.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UploadImage uploadImage;

    private Boolean isDefaultProfileImage;

    public static ProfileImage create(UploadImage uploadImage, Boolean isDefaultProfileImage) {
        ProfileImage profileImage = new ProfileImage();
        profileImage.uploadImage = uploadImage;
        profileImage.isDefaultProfileImage = isDefaultProfileImage;
        return profileImage;
    }
}
