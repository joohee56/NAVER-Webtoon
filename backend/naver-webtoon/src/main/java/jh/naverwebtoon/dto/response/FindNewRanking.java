package jh.naverwebtoon.dto.response;

import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindNewRanking {
    private Long webtoonId;
    private String webtoonName;
    private WebtoonThumbnail thumbnail;
    private Long totalLikeCount;
}
