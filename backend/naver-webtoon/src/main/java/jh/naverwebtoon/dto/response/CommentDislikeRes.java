package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDislikeRes {
    private Long dislikeTotalCnt;
    private Long isUserDislike;
}
