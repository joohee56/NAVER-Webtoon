package jh.naverwebtoon.dto.response;

import java.time.DayOfWeek;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FindOfficialWebtoonPopularityRes {
    private Long webtoonId;
    private String webtoonName;
    private String posterStoreFileName;
    private DayOfWeek dayOfWeek;
    private Long roundUpdateCount;
    private Long totalLikeCount;

    public FindOfficialWebtoonPopularityRes(Webtoon webtoon, Long roundUpdateCount, Long totalLikeCount) {
        OfficialWebtoon officialWebtoon = (OfficialWebtoon) webtoon;
        this.webtoonId = officialWebtoon.getId();
        this.webtoonName = officialWebtoon.getName();
        this.posterStoreFileName = officialWebtoon.getWebtoonThumbnail().getPosterImage()
                .getStoreFileName();
        this.dayOfWeek = officialWebtoon.getDayOfWeek();
        this.roundUpdateCount = roundUpdateCount;
        this.totalLikeCount = totalLikeCount;
    }
}
