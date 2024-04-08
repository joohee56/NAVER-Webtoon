package jh.naverwebtoon.web.controller;

import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonDetailRes;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes;
import jh.naverwebtoon.service.OfficialWebtoonService;
import jh.naverwebtoon.service.WebtoonGenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webtoon/official")
@RequiredArgsConstructor
public class OfficialWebtoonController {
    private final OfficialWebtoonService officialWebtoonService;
    private final WebtoonGenreService webtoonGenreService;

    /**
     * 장르별 웹툰 리스트 조회
     */
    @PostMapping()
    public List<FindOfficialWebtoonsRes> findAll(@RequestBody List<GenreEnum> genres) {
        if (genres.get(0) == GenreEnum.ALL) {
            return officialWebtoonService.findAllOfficialWebtoon();
        } else {
            return webtoonGenreService.findOfficialWebtoonsByGenre(genres);
        }
    }

    @GetMapping("/{id}")
    public FindOfficialWebtoonDetailRes findWebtoonDetail(@PathVariable("id") Long webtoonId) {
        return officialWebtoonService.findOfficialWebtoonDetail(webtoonId);
    }
}
