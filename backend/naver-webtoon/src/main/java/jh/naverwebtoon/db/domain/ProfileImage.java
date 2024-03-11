package jh.naverwebtoon.db.domain;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class ProfileImage {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    @AttributeOverride(name="uploadFileName", column = @Column(name = "Profile_Upload_File_Name"))
    @AttributeOverride(name = "storeFileName", column = @Column(name = "Profile_Store_File_Name"))
    private UploadImage uploadImage;

}
