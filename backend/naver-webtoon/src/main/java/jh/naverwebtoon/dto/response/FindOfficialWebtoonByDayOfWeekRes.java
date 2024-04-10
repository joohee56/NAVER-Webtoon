package jh.naverwebtoon.dto.response;

import java.time.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindOfficialWebtoonByDayOfWeekRes {
    private Long webtoonId;
    private String webtoonName;
    private String thumbnail;
    private DayOfWeek dayOfWeek;
    private Long totalLikeCount;
    private Long roundUpdateCount;
}
