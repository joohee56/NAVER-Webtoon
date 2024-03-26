package jh.naverwebtoon.dto.response;

import jh.naverwebtoon.db.domain.Round;
import lombok.Data;

@Data
public class FindRoundsByWebtoon {
    private Long roundId;
    private Long roundNumber;
    private String title;
    private String thumbnail;

    public static FindRoundsByWebtoon create(Round round) {
        FindRoundsByWebtoon roundsByWebtoon = new FindRoundsByWebtoon();
        roundsByWebtoon.roundId = round.getId();
        roundsByWebtoon.roundNumber = round.getRoundNumber();
        roundsByWebtoon.title = round.getRoundTitle();
        roundsByWebtoon.thumbnail = round.getRoundThumbnail().getThumbnail().getStoreFileName();
        return roundsByWebtoon;
    }

}
