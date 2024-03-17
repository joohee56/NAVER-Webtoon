package jh.naverwebtoon.dto.response;

import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Data;

@Data
public class CreateWebtoonRes {
    private Long id;
    private String name;

    public static CreateWebtoonRes create(Webtoon webtoon) {
        CreateWebtoonRes createWebtoonRes = new CreateWebtoonRes();
        createWebtoonRes.id = webtoon.getId();
        createWebtoonRes.name = webtoon.getName();
        return createWebtoonRes;
    }
}
