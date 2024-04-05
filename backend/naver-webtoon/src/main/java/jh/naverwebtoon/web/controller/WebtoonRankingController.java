package jh.naverwebtoon.web.controller;

import jh.naverwebtoon.dto.response.FindWebtoonRankingsRes;
import jh.naverwebtoon.service.WebtoonRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webtoon/ranking")
@RequiredArgsConstructor
public class WebtoonRankingController {
    private final WebtoonRankingService webtoonRankingService;

    @GetMapping()
    public FindWebtoonRankingsRes findWebtoonRanking() {
        return FindWebtoonRankingsRes.create(webtoonRankingService.findRanking());
    }
}
