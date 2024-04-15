package jh.naverwebtoon.dto.response;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindWebtoonDetailRes {
    private String webtoonName;
    private String posterStoreFileName;
    private String memberName;
    private String profileStoreFileName;
    private DayOfWeek dayOfWeek;
    private Integer ageLimit;
    private String oneLineSummary;
    private String summary;
    private List<String> genres;

    public static FindWebtoonDetailRes create(Webtoon webtoon) {
        FindWebtoonDetailRes res = new FindWebtoonDetailRes();
        res.webtoonName = webtoon.getName();
        res.posterStoreFileName = webtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName();
        res.memberName = webtoon.getMember().getName();
        res.profileStoreFileName = webtoon.getMember().getProfileImage().getUploadImage().getStoreFileName();
        res.ageLimit = webtoon.getAgeLimit();
        res.oneLineSummary = webtoon.getOneLineSummary();
        res.summary = webtoon.getSummary();
        res.genres = webtoon.getGenres().stream()
                .map(webtoonGenre -> webtoonGenre.getGenre().getTitle())
                .collect(Collectors.toList());
        if(webtoon.getClass().equals(OfficialWebtoon.class)) {
            OfficialWebtoon officialWebtoon = (OfficialWebtoon) webtoon;
            res.dayOfWeek = officialWebtoon.getDayOfWeek();
        }
        return res;
    }

}
