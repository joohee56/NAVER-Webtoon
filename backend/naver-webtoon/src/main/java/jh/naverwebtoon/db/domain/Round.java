package jh.naverwebtoon.db.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.util.RoundEntityListener;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EntityListeners(value = {RoundEntityListener.class})
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "round_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="webtoon_id")
    private Webtoon webtoon;

    private Long roundNumber;

    private String roundTitle;

    @Column(length = 300)
    private String authorNote;
}
