package jh.naverwebtoon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
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
    private Long totalLikeCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;

    public FindOfficialWebtoonsRes(Webtoon webtoon, Long roundUpdateCount, Long totalLikeCount, LocalDateTime updatedAt) {
        OfficialWebtoon officialWebtoon = (OfficialWebtoon) webtoon;
        this.webtoonId = officialWebtoon.getId();
        this.webtoonName = officialWebtoon.getName();
        this.posterStoreFileName = officialWebtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName();
        this.dayOfWeek = officialWebtoon.getDayOfWeek();
        this.roundUpdateCount = roundUpdateCount;
        this.totalLikeCount = totalLikeCount;
        this.updatedAt = updatedAt;
    }
}
