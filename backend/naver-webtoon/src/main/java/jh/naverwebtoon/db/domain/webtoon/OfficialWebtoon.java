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
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorValue("official")
public class OfficialWebtoon extends Webtoon {

    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private boolean isComplete;

    public OfficialWebtoon(Member member, CreateWebtoonReq createWebtoonReq, List<Genre> genres,
                           WebtoonThumbnail webtoonThumbnail, DayOfWeek dayOfWeek) {
        super(member, createWebtoonReq, genres, webtoonThumbnail);
        this.dayOfWeek = dayOfWeek;
    }
}
