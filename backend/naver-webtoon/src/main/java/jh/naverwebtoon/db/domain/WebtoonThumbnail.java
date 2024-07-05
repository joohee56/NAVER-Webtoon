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
public class WebtoonThumbnail extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name="uploadFileName", column = @Column(name = "poster_upload_file_name"))
    @AttributeOverride(name = "storeFileName", column = @Column(name = "poster_store_file_name"))
    private UploadImage posterImage;

    @Embedded
    @AttributeOverride(name="uploadFileName", column = @Column(name = "horizontal_upload_file_name"))
    @AttributeOverride(name = "storeFileName", column = @Column(name = "horizontal_store_file_name"))
    private UploadImage horizontalImage;

    public static WebtoonThumbnail create(UploadImage posterImage, UploadImage horizontalImage) {
        WebtoonThumbnail webtoonThumbnail = new WebtoonThumbnail();
        webtoonThumbnail.posterImage = posterImage;
        webtoonThumbnail.horizontalImage = horizontalImage;
        return webtoonThumbnail;
    }

    public void changeThumbnail(UploadImage posterImage, UploadImage horizontalImage) {
        if (posterImage != null) {
            this.posterImage = posterImage;
        }
        if(horizontalImage != null) {
            this.horizontalImage = horizontalImage;
        }
    }
}
