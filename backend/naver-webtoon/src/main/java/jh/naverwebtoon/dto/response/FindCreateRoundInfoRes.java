package jh.naverwebtoon.dto.response;

import lombok.Data;

@Data
public class FindCreateRoundInfoRes {
    private Long webtoonId;
    private String webtoonName;
    private Long roundNumber;

    public FindCreateRoundInfoRes(Long webtoonId, String webtoonName, Long roundNumber) {
        this.webtoonId = webtoonId;
        this.webtoonName = webtoonName;
        this.roundNumber = roundNumber == null ? 1 : roundNumber + 1;
    }
}
