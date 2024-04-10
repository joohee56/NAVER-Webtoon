package jh.naverwebtoon.dto.response;

import java.time.DayOfWeek;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FindOfficialWebtoonsRes {
    private Long webtoonId;
    private String webtoonName;
    private String posterStoreFileName;
    private DayOfWeek dayOfWeek;
    private Long roundUpdateCount;

    public FindOfficialWebtoonsRes(Webtoon webtoon, Long roundUpdateCount) {
        OfficialWebtoon officialWebtoon = (OfficialWebtoon) webtoon;
        this.webtoonId = officialWebtoon.getId();
        this.webtoonName = officialWebtoon.getName();
        this.posterStoreFileName = officialWebtoon.getWebtoonThumbnail().getPosterImage()
                .getStoreFileName();
        this.dayOfWeek = officialWebtoon.getDayOfWeek();
        this.roundUpdateCount = roundUpdateCount;
    }
}
