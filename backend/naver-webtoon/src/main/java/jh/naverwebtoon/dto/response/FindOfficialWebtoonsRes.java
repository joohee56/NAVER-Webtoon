package jh.naverwebtoon.dto.response;

import java.time.DayOfWeek;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import lombok.Data;

@Data
public class FindOfficialWebtoonsRes {
    private Long webtoonId;
    private String webtoonName;
    private String posterStoreFileName;
    private DayOfWeek dayOfWeek;
    private boolean isComplete;

    public static FindOfficialWebtoonsRes create(OfficialWebtoon officialWebtoon) {
        FindOfficialWebtoonsRes findOfficialWebtoonsRes = new FindOfficialWebtoonsRes();
        findOfficialWebtoonsRes.webtoonId = officialWebtoon.getId();
        findOfficialWebtoonsRes.webtoonName = officialWebtoon.getName();
        findOfficialWebtoonsRes.posterStoreFileName = officialWebtoon.getWebtoonThumbnail().getPosterImage()
                .getStoreFileName();
        findOfficialWebtoonsRes.dayOfWeek = officialWebtoon.getDayOfWeek();
        findOfficialWebtoonsRes.isComplete = officialWebtoon.isComplete();
        return findOfficialWebtoonsRes;
    }
}
