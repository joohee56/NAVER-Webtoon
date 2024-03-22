package jh.naverwebtoon.dto.response;

import lombok.Data;

@Data
public class FindCreateRoundInfoRes {
    private Long webtoonId;
    private String webtoonName;
    private Long roundNumber;

    public static FindCreateRoundInfoRes create(Long webtoonId, String webtoonName, Object roundNumber) {
        FindCreateRoundInfoRes res = new FindCreateRoundInfoRes();
        res.webtoonId = webtoonId;
        res.webtoonName = webtoonName;
        res.roundNumber = roundNumber == null ? 1 : (Long) roundNumber + 1;
        return res;
    }
}
