package jh.naverwebtoon.db.domain.webtoon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.DayOfWeek;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;

@Entity
public class OfficialSerialWebtoon extends Webtoon {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(nullable = false)
    private boolean isComplete;
}
