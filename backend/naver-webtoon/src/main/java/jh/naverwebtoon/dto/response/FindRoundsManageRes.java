package jh.naverwebtoon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindRoundsManageRes {
    private Long roundId;
    private Long roundNumber;
    private String roundThumbnail;
    private String roundTitle;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;
    private Long totalLikeCount;
    private Long totalCommentCount;

    @Override
    public String toString() {
        return "FindRoundsManageRes{" +
                "roundId=" + roundId +
                ", roundNumber=" + roundNumber +
                ", roundThumbnail='" + roundThumbnail + '\'' +
                ", roundTitle='" + roundTitle + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", totalLikeCount=" + totalLikeCount +
                ", totalCommentCount=" + totalCommentCount +
                '}';
    }
}
