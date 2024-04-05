package jh.naverwebtoon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class FindWebtoonRankingsRes {
    private List<WebtoonRankingDto> rankings;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedAt;

    public static FindWebtoonRankingsRes create(List<WebtoonRankingDto> rankings) {
        FindWebtoonRankingsRes res = new FindWebtoonRankingsRes();
        res.rankings = rankings;
        res.updatedAt = LocalDateTime.now();
        return res;
    }
}
