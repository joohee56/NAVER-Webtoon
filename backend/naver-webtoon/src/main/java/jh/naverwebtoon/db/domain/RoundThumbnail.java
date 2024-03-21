package jh.naverwebtoon.db.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class RoundThumbnail {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private UploadImage thumbnail;
}
