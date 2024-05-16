package jh.naverwebtoon.db.domain.webtoon;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.DayOfWeek;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.dto.request.CreateOfficialWebtoonReq;
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

    public OfficialWebtoon(Member member, CreateOfficialWebtoonReq createOfficialWebtoonReq, WebtoonThumbnail webtoonThumbnail) {
        super(member, createOfficialWebtoonReq, webtoonThumbnail);
        this.dayOfWeek = createOfficialWebtoonReq.getDayOfWeek();
    }
}
