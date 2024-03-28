package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentLikeRes {
    private Long likeCnt;
    private Boolean isUserLike;
}
