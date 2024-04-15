package jh.naverwebtoon.web.controller;

import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.response.FindWebtoonRankingsRes;
import jh.naverwebtoon.service.WebtoonRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webtoon/ranking")
@RequiredArgsConstructor
public class WebtoonRankingController {
    private final WebtoonRankingService webtoonRankingService;

    @GetMapping("/{offset}/{limit}/{webtoonType}")
    public FindWebtoonRankingsRes findWebtoonRanking(@PathVariable("offset") int offset, @PathVariable("limit") int limit, @PathVariable("webtoonType") WebtoonType webtoonType) {
        return FindWebtoonRankingsRes.create(webtoonRankingService.findRanking(offset, limit, webtoonType));
    }
}
