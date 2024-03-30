package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCommentDislikeRes {
    private Long dislikeTotalCnt;
    private Long isUserDislike;
}
