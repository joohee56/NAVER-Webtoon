package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateRoundLikeRes {
    private Long totalLikeCnt;
    private Long isUserLike;
}
