package jh.naverwebtoon.dto.response;

import jh.naverwebtoon.db.domain.enums.WebtoonType;
import lombok.Data;

@Data
public class FindCreateRoundInfoRes {
    private Long webtoonId;
    private String webtoonName;
    private String webtoonType;
    private Long roundNumber;

    public FindCreateRoundInfoRes(Long webtoonId, String webtoonName, WebtoonType webtoonType, Long roundNumber) {
        this.webtoonId = webtoonId;
        this.webtoonName = webtoonName;
        this.webtoonType = webtoonType.name().toLowerCase();
        this.roundNumber = roundNumber == null ? 1 : roundNumber + 1;
    }
}
