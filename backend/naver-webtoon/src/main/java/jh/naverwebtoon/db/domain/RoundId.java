package jh.naverwebtoon.db.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Getter;

@Embeddable
@Getter
public class RoundId implements Serializable {
    private Webtoon webtoon;
    private Long roundNumber;
}
