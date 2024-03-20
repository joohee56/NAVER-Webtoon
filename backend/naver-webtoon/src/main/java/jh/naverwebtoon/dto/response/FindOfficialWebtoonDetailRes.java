package jh.naverwebtoon.dto.response;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindOfficialWebtoonDetailRes {
    private String posterStoreFileName;
    private String webtoonName;
    private String memberName;
    private String profileStoreFileName;
    private DayOfWeek dayOfWeek;
    private Integer ageLimit;
    private String oneLineSummary;
    private String summary;
    private List<String> tags;

    public static FindOfficialWebtoonDetailRes create(OfficialWebtoon webtoon) {
        FindOfficialWebtoonDetailRes res = new FindOfficialWebtoonDetailRes();
        res.posterStoreFileName = webtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName();
        res.webtoonName = webtoon.getName();
        res.memberName = webtoon.getMember().getName();
        res.profileStoreFileName = webtoon.getMember().getProfileImage().getUploadImage().getStoreFileName();
        res.dayOfWeek = webtoon.getDayOfWeek();
        res.ageLimit = webtoon.getAgeLimit();
        res.oneLineSummary = webtoon.getOneLineSummary();
        res.summary = webtoon.getSummary();
        res.tags = webtoon.getTags().stream()
                .map(tag -> tag.getName())
                .collect(Collectors.toList());
        return res;
    }

}
