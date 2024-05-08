package jh.naverwebtoon.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindEditWebtoonRes {
    private String webtoonName;
    private String webtoonCategory;
    private List<GenreRes> genres;
    private String oneLineSummary;
    private String summary;
    private String posterStoreFileName;
    private String horizontalStoreFileName;

    public static FindEditWebtoonRes create(Webtoon webtoon) {
        FindEditWebtoonRes res = new FindEditWebtoonRes();
        res.webtoonName = webtoon.getName();
        res.webtoonCategory = webtoon.getWebtoonCategory().name();
        res.genres = webtoon.getGenres().stream()
                .map(webtoonGenre -> new GenreRes(webtoonGenre.getGenre().name(), webtoonGenre.getGenre().getTitle()))
                .collect(Collectors.toList());
        res.oneLineSummary = webtoon.getOneLineSummary();
        res.summary = webtoon.getSummary();
        res.posterStoreFileName = webtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName();
        res.horizontalStoreFileName = webtoon.getWebtoonThumbnail().getHorizontalImage().getStoreFileName();
        return res;
    }

}
