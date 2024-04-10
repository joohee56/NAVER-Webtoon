package jh.naverwebtoon.web.controller;

import java.time.DayOfWeek;
import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
import jh.naverwebtoon.db.repository.OfficialWebtoonRepository;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonByDayOfWeekRes;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/webtoon/official")
@RequiredArgsConstructor
public class OfficialWebtoonController {
    private final OfficialWebtoonService officialWebtoonService;
    private final OfficialWebtoonRepository officialWebtoonRepository;
    private final WebtoonGenreService webtoonGenreService;

    /**
     * 웹툰 리스트 조회(전체), 장르별 조회
     * 인기순 / 업데이트순 정렬
     */
    @PostMapping()
    public List<FindOfficialWebtoonsRes> findAll(@RequestBody List<GenreEnum> genres, @RequestParam(name = "sorting") SortingEnum sorting) {
        if (genres.get(0) == GenreEnum.ALL) {
            return officialWebtoonService.findAll(sorting);
        } else {
            return webtoonGenreService.findOfficialWebtoonsByGenre(sorting, genres);
        }
    }


    /**
     * 요일별 웹툰 조회
     */
    @GetMapping("/{dayOfWeek}")
    public List<FindOfficialWebtoonByDayOfWeekRes> findAllByDayOfWeek(@PathVariable("dayOfWeek") DayOfWeek dayOfWeek) {
        return officialWebtoonRepository.findAllByDayOfWeek(dayOfWeek);
    }

    /**
     * 웹툰 디테일 정보 조회
     */
    @GetMapping("/detail/{id}")
    public FindOfficialWebtoonDetailRes findWebtoonDetail(@PathVariable("id") Long webtoonId) {
        return officialWebtoonService.findOfficialWebtoonDetail(webtoonId);
    }


}
