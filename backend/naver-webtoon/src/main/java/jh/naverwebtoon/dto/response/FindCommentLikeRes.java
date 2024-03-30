package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCommentLikeRes {
    private Long likeTotalCnt;
    private Long isUserLike;
}
