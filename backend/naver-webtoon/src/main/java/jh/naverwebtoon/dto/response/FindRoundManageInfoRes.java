package jh.naverwebtoon.dto.response;

import java.util.List;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.Data;

@Data
public class FindRoundManageInfoRes {
    private String webtoonName;
    private String webtoonThumbnail;
    private String summary;
    private List<FindRoundsManageRes> rounds;
    private int totalPageCount;

    public static FindRoundManageInfoRes create(Webtoon webtoon, List<FindRoundsManageRes> rounds, int totalPageCount) {
        FindRoundManageInfoRes res = new FindRoundManageInfoRes();
        res.webtoonName = webtoon.getName();
        res.webtoonThumbnail = webtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName();
        res.summary = webtoon.getSummary();
        res.rounds = rounds;
        res.totalPageCount = totalPageCount;
        return res;
    }
}
