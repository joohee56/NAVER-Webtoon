package jh.naverwebtoon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCreateRoundInfoRes {
    private String webtoonName;
    private int roundNumber;
}
