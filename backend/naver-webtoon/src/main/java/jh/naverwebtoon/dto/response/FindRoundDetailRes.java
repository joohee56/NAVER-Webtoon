package jh.naverwebtoon.dto.response;

import jh.naverwebtoon.db.domain.Round;
import lombok.Data;

@Data
public class FindRoundDetailRes {
    private Long webtoonId;
    private String webtoonName;
    private Long roundNumber;
    private String roundTitle;
    private String mergeManuscript;
    private String authorNote;
    private String authorProfileImage;
    private String authorName;
    private Long totalLikeCnt;
    private Long isUserLike;

    public static FindRoundDetailRes create(Round round, Long totalLikeCnt, Long isUserLike) {
        FindRoundDetailRes findRoundDetailRes = new FindRoundDetailRes();
        findRoundDetailRes.webtoonId = round.getWebtoon().getId();
        findRoundDetailRes.webtoonName = round.getWebtoon().getName();
        findRoundDetailRes.roundNumber = round.getRoundNumber();
        findRoundDetailRes.roundTitle = round.getRoundTitle();
        findRoundDetailRes.mergeManuscript = round.getMergeManuscript().getFile().getStoreFileName();
        findRoundDetailRes.authorNote = round.getAuthorNote();
        findRoundDetailRes.authorProfileImage = round.getWebtoon().getMember().getProfileImage().getUploadImage().getStoreFileName();
        findRoundDetailRes.authorName = round.getWebtoon().getMember().getName();
        findRoundDetailRes.totalLikeCnt = totalLikeCnt;
        findRoundDetailRes.isUserLike = isUserLike;
        return findRoundDetailRes;
    }
}
