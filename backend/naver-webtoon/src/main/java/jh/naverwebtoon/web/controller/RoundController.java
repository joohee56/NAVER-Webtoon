package jh.naverwebtoon.web.controller;

import jh.naverwebtoon.dto.response.FindRoundsByWebtoon;
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

    @GetMapping("/{webtoonId}/{offset}/{limit}/{isDesc}")
    private FindRoundsByWebtoon getRoundsByWebtoonWithPaging(@PathVariable("webtoonId") Long webtoonId, @PathVariable("offset") int offset, @PathVariable("limit") int limit, @PathVariable("isDesc") boolean isDesc) {
        return roundService.findRoundsByWebtoonWithPaing(webtoonId, offset, limit, isDesc);
    }
}
