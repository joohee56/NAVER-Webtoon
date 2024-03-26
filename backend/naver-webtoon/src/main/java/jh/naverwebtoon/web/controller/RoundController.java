package jh.naverwebtoon.web.controller;

import java.util.List;
import jh.naverwebtoon.dto.response.FindRoundDetailRes;
import jh.naverwebtoon.dto.response.FindRoundsByWebtoon;
import jh.naverwebtoon.dto.response.FindRoundsByWebtoonWithPaging;
import jh.naverwebtoon.service.RoundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/rounds")
@RequiredArgsConstructor
public class RoundController {
    private final RoundService roundService;

    /**
     * 웹툰에 해당하는 회차 리스트 조회 (페이징)
     */
    @GetMapping("/{webtoonId}/{offset}/{limit}/{isDesc}")
    public FindRoundsByWebtoonWithPaging getRoundsByWebtoonWithPaging(@PathVariable("webtoonId") Long webtoonId, @PathVariable("offset") int offset, @PathVariable("limit") int limit, @PathVariable("isDesc") boolean isDesc) {
        return roundService.findRoundsByWebtoonWithPaing(webtoonId, offset, limit, isDesc);
    }

    @GetMapping("/{webtoonId}")
    public List<FindRoundsByWebtoon> getRoundsByWebtoon(@PathVariable("webtoonId") Long webtoonId) {
        return roundService.findRoundsByWebtoon(webtoonId);
    }

    /**
     * 회차 디테일 조회
     */
    @GetMapping("/round/{roundId}")
    public FindRoundDetailRes getRoundDetail(@PathVariable("roundId") Long roundId) {
        return roundService.findOneDetail(roundId);
    }
}
