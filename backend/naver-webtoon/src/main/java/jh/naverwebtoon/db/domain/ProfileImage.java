package jh.naverwebtoon.db.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
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
public class ProfileImage {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "profile_image_id")
    private Long id;

    @Embedded
    @AttributeOverride(name="uploadFileName", column = @Column(name = "profile_upload_file_name"))
    @AttributeOverride(name = "storeFileName", column = @Column(name = "profile_store_file_name"))
    private UploadImage uploadImage;

    public static ProfileImage createProfileImage(UploadImage uploadImage) {
        ProfileImage profileImage = new ProfileImage();
        profileImage.uploadImage = uploadImage;
        return profileImage;
    }

}
