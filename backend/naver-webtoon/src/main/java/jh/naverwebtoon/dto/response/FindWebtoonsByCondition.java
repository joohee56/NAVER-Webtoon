package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindOfficialWebtoonByDayOfWeekRes {
    private Long webtoonId;
    private String webtoonName;
    private String thumbnail;
    private Long totalLikeCount;
    private Long roundUpdateCount;
}
