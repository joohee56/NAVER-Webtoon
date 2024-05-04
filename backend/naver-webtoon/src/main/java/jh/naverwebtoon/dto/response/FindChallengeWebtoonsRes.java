package jh.naverwebtoon.dto.response;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindChallengeWebtoonsRes {
    private Map<String, List<WebtoonByGenreDto>> webtoonMap;
    private Integer totalPageCount;
}
