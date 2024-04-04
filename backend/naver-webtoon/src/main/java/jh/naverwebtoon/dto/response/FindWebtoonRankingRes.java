package jh.naverwebtoon.dto.response;

import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.enums.RankingStatus;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Data;

@Data
public class FindWebtoonRankingRes {
    private Long webtoonId;
    private String webtoonName;
    private String thumbnail;
    private List<String> genres;
    private Long totalLikeCount;
    private RankingStatus rankingStatus;

    public static FindWebtoonRankingRes create(Webtoon webtoon, Long totalLikeCount, int ranking, List<Long> latestRanking) {
        FindWebtoonRankingRes res = new FindWebtoonRankingRes();
        res.webtoonId = webtoon.getId();
        res.webtoonName = webtoon.getName();
        res.thumbnail = webtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName();
        res.genres = webtoon.getGenres().stream()
                .map(webtoonGenre -> webtoonGenre.getGenre().getGenreEnum().getTitle())
                .collect(Collectors.toList());
        res.totalLikeCount = totalLikeCount;
        res.rankingStatus = calculateStatus(res.webtoonId, ranking, latestRanking);
        return res;
    }

    private static RankingStatus calculateStatus(Long webtoonId, int ranking, List<Long> latestRanking) {
        if (latestRanking.isEmpty() || !latestRanking.contains(webtoonId)) {
            return RankingStatus.UP;
        } else {
            for(int i=0; i<latestRanking.size(); i++) {
                if (latestRanking.get(i) == webtoonId) {
                    if (ranking < i) {
                        return RankingStatus.UP;
                    } else if(ranking > i) {
                        return RankingStatus.DOWN;
                    } else {
                        return RankingStatus.UNCHANGING;
                    }
                }
            }
            return RankingStatus.UNCHANGING;
        }
    }
}
