package jh.naverwebtoon.dto.response;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import lombok.Data;

@Data
public class FindOfficialWebtoonsRes {
    private Long webtoonId;
    private String webtoonName;
    private List<GenreEnum> genres;
    private int ageLimit;
    private String memberName;
    private String posterStoreFileName;
    private DayOfWeek dayOfWeek;
    private boolean isComplete;

    public static FindOfficialWebtoonsRes create(OfficialWebtoon officialWebtoon) {
        FindOfficialWebtoonsRes findOfficialWebtoonsRes = new FindOfficialWebtoonsRes();
        findOfficialWebtoonsRes.webtoonId = officialWebtoon.getId();
        findOfficialWebtoonsRes.webtoonName = officialWebtoon.getName();
        findOfficialWebtoonsRes.genres = officialWebtoon.getGenres().stream()
                .map(webtoonGenre -> webtoonGenre.getGenre().getGenreEnum())
                .collect(Collectors.toList());
        findOfficialWebtoonsRes.ageLimit = officialWebtoon.getAgeLimit();
        findOfficialWebtoonsRes.memberName = officialWebtoon.getMember().getName();
        findOfficialWebtoonsRes.posterStoreFileName = officialWebtoon.getWebtoonThumbnail().getPosterImage()
                .getStoreFileName();
        findOfficialWebtoonsRes.dayOfWeek = officialWebtoon.getDayOfWeek();
        findOfficialWebtoonsRes.isComplete = officialWebtoon.isComplete();
        return findOfficialWebtoonsRes;
    }
}
