package jh.naverwebtoon.db.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class RoundThumbnail {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private UploadImage thumbnail;

    public static RoundThumbnail create(UploadImage thumbnail) {
        RoundThumbnail roundThumbnail = new RoundThumbnail();
        roundThumbnail.thumbnail = thumbnail;
        return roundThumbnail;
    }
}
