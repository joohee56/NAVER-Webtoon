package jh.naverwebtoon.db.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Manuscript {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private UploadImage file;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "round_id")
    private Round round;

    public void setRound(Round round) {
        this.round = round;
    }
}
