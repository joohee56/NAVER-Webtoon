package jh.naverwebtoon.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindChallengeWebtoonsByGenreRes {
    private List<WebtoonByGenreDto> webtoons;
    private int totalPageCount;
}
