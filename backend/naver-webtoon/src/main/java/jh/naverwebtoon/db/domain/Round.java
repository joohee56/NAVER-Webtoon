package jh.naverwebtoon.db.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@IdClass(RoundId.class)
public class Round {
    @Id
    @ManyToOne
    @JoinColumn(name="webtoon_id")
    private Webtoon webtoon;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roundNumber;

    private String roundTitle;

    @Column(length = 300)
    private String authorNote;
}
