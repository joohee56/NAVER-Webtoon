package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchCountRes {
    private Long totalOfficialCount;
    private Long totalChallengeCount;
}
