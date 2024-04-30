package jh.naverwebtoon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Data;

@Data
public class SearchWebtoonDto {
    private Long webtoonId;
    private String webtoonName;
    private String thumbnail;
    private String authorName;
    private String summary;
    private DayOfWeek dayOfWeek;
    private Long totalRoundCount;
    @JsonFormat(pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime recentUpdatedAt;

    public SearchWebtoonDto(Webtoon webtoon, Long totalRoundCount, LocalDateTime recentUpdatedAt) {
        this.webtoonId = webtoon.getId();
        this.webtoonName = webtoon.getName();
        this.thumbnail = webtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName();
        this.authorName = webtoon.getMember().getName();
        this.summary = webtoon.getSummary();
        if (webtoon.getWebtoonType()== WebtoonType.OFFICIAL) {
            OfficialWebtoon officialWebtoon = (OfficialWebtoon) webtoon;
            this.dayOfWeek = officialWebtoon.getDayOfWeek();
        }
        this.totalRoundCount = totalRoundCount;
        this.recentUpdatedAt = recentUpdatedAt;
    }
}
