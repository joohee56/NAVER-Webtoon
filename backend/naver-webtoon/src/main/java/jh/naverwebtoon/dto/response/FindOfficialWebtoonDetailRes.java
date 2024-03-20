package jh.naverwebtoon.dto.response;

import java.time.DayOfWeek;
import java.util.List;
import lombok.Data;

@Data
public class FindWebtoonDetailRes {
    private String posterStoreFileName;
    private String webtoonName;
    private String memberName;
    private String profileStoreFileName;
    private DayOfWeek dayOfWeek;
    private Integer ageLimit;
    private String oneLineSummary;
    private String summmary;
    private List<String> tags;
}
