package jh.naverwebtoon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDto {
    private Long commentId;
    private String content;
    private String userId;
    private String userName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updateAt;
    private Long likeTotalCnt;
    private Long isUserLike;
    private Long dislikeTotalCnt;
    private Long isUserDislike;
    private Long replyCnt;
}
