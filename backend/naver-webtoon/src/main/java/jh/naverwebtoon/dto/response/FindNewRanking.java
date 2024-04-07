package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindNewRanking {
    private Long webtoonId;
    private Long totalLikeCount;
}
