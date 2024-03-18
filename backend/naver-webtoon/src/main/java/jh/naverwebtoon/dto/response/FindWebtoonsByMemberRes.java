package jh.naverwebtoon.dto.response;

import jakarta.persistence.DiscriminatorValue;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Data;

@Data
public class FindWebtoonsByMemberRes {
    private Long webtoonId;
    private String webtoonName;
    private String posterStoreName;
    private String serialType;

    public static FindWebtoonsByMemberRes create(Webtoon webtoon) {
        FindWebtoonsByMemberRes res = new FindWebtoonsByMemberRes();
        res.webtoonId  = webtoon.getId();
        res.webtoonName = webtoon.getName();
        res.posterStoreName = webtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName();
        res.serialType = webtoon.getClass().getAnnotation(DiscriminatorValue.class).value();
        return res;
    }
}
