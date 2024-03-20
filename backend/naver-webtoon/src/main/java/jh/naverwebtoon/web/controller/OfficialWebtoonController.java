package jh.naverwebtoon.web.controller;

import java.util.List;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonDetailRes;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes;
import jh.naverwebtoon.service.OfficialWebtoonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webtoon/official")
@RequiredArgsConstructor
public class OfficialWebtoonController {
    private final OfficialWebtoonService officialWebtoonService;

    @GetMapping()
    public List<FindOfficialWebtoonsRes> findAll() {
        return officialWebtoonService.findAllOfficialWebtoon();
    }

    @GetMapping("/{id}")
    public FindOfficialWebtoonDetailRes findWebtoonDetail(@PathVariable("id") Long webtoonId) {
        return officialWebtoonService.findOfficialWebtoonDetail(webtoonId);
    }
}
