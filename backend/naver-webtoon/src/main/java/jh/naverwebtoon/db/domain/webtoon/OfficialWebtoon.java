package jh.naverwebtoon.db.domain.webtoon;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.DayOfWeek;
import java.util.List;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("official")
public class OfficialWebtoon extends Webtoon {

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private boolean isComplete;

    public OfficialWebtoon(Member member, CreateWebtoonReq createWebtoonReq, List<Genre> genres,
                           WebtoonThumbnail webtoonThumbnail, DayOfWeek dayOfWeek) {
        super(member, createWebtoonReq, genres, webtoonThumbnail, WebtoonType.OFFICIAL);
        this.dayOfWeek = dayOfWeek;
    }
}
