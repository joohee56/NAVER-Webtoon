package jh.naverwebtoon.dto.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.WebtoonGenre;
import jh.naverwebtoon.db.domain.WebtoonRanking;
import jh.naverwebtoon.db.domain.enums.RankingStatus;
import lombok.Data;

@Data
public class WebtoonRankingDto {
    private Long webtoonId;
    private String webtoonName;
    private String thumbnail;
    private List<String> genres = new ArrayList<>();
    private Long totalLikeCount;
    private String rankingStatus;
    private int ranking;

    public static WebtoonRankingDto create(FindNewRanking findNewRanking, RankingStatus rankingStatus, List<Genre> genres, int ranking) {
        WebtoonRankingDto res = new WebtoonRankingDto();
        res.webtoonId = findNewRanking.getWebtoonId();
        res.webtoonName = findNewRanking.getWebtoonName();
        res.thumbnail = findNewRanking.getThumbnail().getPosterImage().getStoreFileName();
        res.genres = genres.stream()
                .map(genre -> genre.getGenreEnum().getTitle())
                .collect(Collectors.toList());
        res.totalLikeCount = findNewRanking.getTotalLikeCount();
        res.rankingStatus = rankingStatus.getName();
        res.ranking = ranking;
        return res;
    }

    public static WebtoonRankingDto create(WebtoonRanking webtoonRanking) {
        WebtoonRankingDto res = new WebtoonRankingDto();
        res.webtoonId = webtoonRanking.getWebtoon().getId();
        res.webtoonName = webtoonRanking.getWebtoon().getName();
        res.thumbnail = webtoonRanking.getWebtoon().getWebtoonThumbnail().getPosterImage().getStoreFileName();
        for (WebtoonGenre webtoonGenre : webtoonRanking.getWebtoon().getGenres()) {
            res.genres.add(webtoonGenre.getGenre().getGenreEnum().getTitle());
        }
        res.totalLikeCount = webtoonRanking.getTotalLikeCount();
        res.rankingStatus = webtoonRanking.getStatus().getName();
        res.ranking = webtoonRanking.getRanking().ordinal();
        return res;
    }
}
