package jh.naverwebtoon.dto.response;

import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Data;

@Data
public class CreateWebtoonRes {
    private Long id;
    private String name;
    private String posterStoreName;

    public static CreateWebtoonRes create(Webtoon webtoon) {
        CreateWebtoonRes createWebtoonRes = new CreateWebtoonRe();
        createWebtoonRes.id = webtoon.getId();
        createWebtoonRes.name = webtoon.getName();
        createWebtoonRes.posterStoreName = webtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName();
        return createWebtoonRes;
    }
}
