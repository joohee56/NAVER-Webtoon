package jh.naverwebtoon.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchRes {
    private List<SearchWebtoonDto> officials;
    private List<SearchWebtoonDto> challenges;
    private Long totalOfficialCount;
    private Long totalChallengeCount;
}
