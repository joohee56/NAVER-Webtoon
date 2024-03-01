package jh.naverwebtoon.db.domain.webtoon;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.DayOfWeek;

@Entity
public class OfficialSerialWebtoon extends Webtoon {

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private boolean isComplete;
}
