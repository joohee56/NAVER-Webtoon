package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindWebtoonsByCondition {
    private Long webtoonId;
    private String webtoonName;
    private String posterStoreFileName;
    private Long totalLikeCount;
    private Long roundUpdateCount;
}
