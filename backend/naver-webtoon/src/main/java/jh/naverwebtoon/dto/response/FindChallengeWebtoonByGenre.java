package jh.naverwebtoon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FindChallengeWebtoonsRes {
    private Long webtoonId;
    private String webtoonName;
    private String posterStoreFileName;
    private Long roundUpdateCount;
    private Long totalLikeCount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;

    public FindChallengeWebtoonsRes(Webtoon webtoon, Long roundUpdateCount, Long totalLikeCount, LocalDateTime updatedAt) {
        this.webtoonId = webtoon.getId();
        this.webtoonName = webtoon.getName();
        this.posterStoreFileName = webtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName();
        this.roundUpdateCount = roundUpdateCount;
        this.totalLikeCount = totalLikeCount;
        this.updatedAt = updatedAt;
    }
}
