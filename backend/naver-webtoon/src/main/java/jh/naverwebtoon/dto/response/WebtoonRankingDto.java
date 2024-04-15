package jh.naverwebtoon.dto.response;

import java.util.ArrayList;
import java.util.List;
import jh.naverwebtoon.db.domain.WebtoonGenre;
import jh.naverwebtoon.db.domain.WebtoonRanking;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class WebtoonRankingDto {
    private Long webtoonId;
    private String webtoonName;
    private String thumbnail;
    private List<String> genres = new ArrayList<>();
    private Long totalLikeCount;
    private String rankingStatus;
    private int rankingNum;

    public static WebtoonRankingDto create(WebtoonRanking webtoonRanking) {
        WebtoonRankingDto res = new WebtoonRankingDto();
        res.webtoonId = webtoonRanking.getWebtoon().getId();
        res.webtoonName = webtoonRanking.getWebtoon().getName();
        res.thumbnail = webtoonRanking.getWebtoon().getWebtoonThumbnail().getPosterImage().getStoreFileName();
        for (WebtoonGenre webtoonGenre : webtoonRanking.getWebtoon().getGenres()) {
            res.genres.add(webtoonGenre.getGenre().getTitle());
        }
        res.totalLikeCount = webtoonRanking.getTotalLikeCount();
        res.rankingStatus = webtoonRanking.getStatus().getName();
        res.rankingNum = webtoonRanking.getRanking().ordinal()+1;
        return res;
    }
}
