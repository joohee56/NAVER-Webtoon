package jh.naverwebtoon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCommentsByRoundRes {
    private Long commentId;
    private String content;
    private String loginId;
    private String memberName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;
    private Long likeTotalCnt;
    private Long isUserLike;
    private Long disLikeTotalCnt;
    private Long isUserDislike;

    @Override
    public String toString() {
        return "FindCommentsByRoundRes{" +
                "commentId=" + commentId +
                ", content='" + content + '\'' +
                ", loginId='" + loginId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", updatedAt=" + updatedAt +
                ", likeTotalCnt=" + likeTotalCnt +
                ", isUserLike=" + isUserLike +
                ", disLikeTotalCnt=" + disLikeTotalCnt +
                ", isUserDislike=" + isUserDislike +
                '}';
    }
}
