package jh.naverwebtoon.db.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class UploadImage {
    private String uploadFileName;
    private String storeFileName;
}
