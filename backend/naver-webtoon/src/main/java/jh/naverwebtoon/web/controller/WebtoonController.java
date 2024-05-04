package jh.naverwebtoon.web.controller;

import jakarta.validation.Valid;
import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import jh.naverwebtoon.dto.response.FindChallengeWebtoonsByGenreRes;
import jh.naverwebtoon.dto.response.FindChallengeWebtoonsRes;
import jh.naverwebtoon.dto.response.FindCreateRoundInfoRes;
import jh.naverwebtoon.dto.response.FindWebtoonDetailRes;
import jh.naverwebtoon.dto.response.FindWebtoonsByMemberRes;
import jh.naverwebtoon.dto.response.SearchCountRes;
import jh.naverwebtoon.dto.response.SearchRes;
import jh.naverwebtoon.dto.response.SearchWebtoonDto;
import jh.naverwebtoon.service.WebtoonGenreService;
import jh.naverwebtoon.service.WebtoonService;
import jh.naverwebtoon.web.Auth;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/webtoon")
@Slf4j
public class WebtoonController {
    private final WebtoonService webtoonService;
    private final WebtoonGenreService webtoonGenreService;

    /**
     *  웹툰 등록
     */
    @Auth
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long createWebtoon(@Login Long id, @Valid @ModelAttribute CreateWebtoonReq createWebtoonReq) {
        Webtoon webtoon = webtoonService.createWebtoon(id, createWebtoonReq);
        return webtoon.getId();
    }

    /**
     * 회원에 속한 웹툰 정보 조회
     */
    @Auth
    @GetMapping()
    public List<FindWebtoonsByMemberRes> findAllByMember(@Login Long id) {
        return webtoonService.findAllByMember(id);
    }

    /**
     * 회차 등록 시 필요한 정보 조회
     */
    @Auth
    @GetMapping("/createRound")
    public List<FindCreateRoundInfoRes> findCreateRoundInfo(@Login Long id) {
        return webtoonService.findCreateRoundInfo(id);
    }

    /**
     * 전체 도전웹툰 조회
     */
    @GetMapping("/all")
    public FindChallengeWebtoonsRes findChallengeWebtoonAll(@RequestParam("offset") int offset, @RequestParam("limit") int limit, @RequestParam("sorting") SortingEnum sorting) {
        return webtoonGenreService.findChallengeWebtoonAll(offset, limit, sorting);
    }

    /**
     * 장르별 전체 도전웹툰 조회
     */
    @GetMapping("/genres/{genre}")
    public FindChallengeWebtoonsByGenreRes findChallengeWebtoonAllByGenre(@PathVariable("genre") GenreEnum genre, @RequestParam("sorting") SortingEnum sorting, @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        return webtoonGenreService.findChallengeWebtoonAllByGenre(genre, sorting, offset, limit);
    }

    /**
     * 회차 리스트에서 웹툰 정보 조회
     */
    @GetMapping("/detail/{id}")
    public FindWebtoonDetailRes findWebtoonDetail(@PathVariable("id") Long webtoonId) {
        return webtoonService.findWebtoonDetail(webtoonId);
    }

    /**
     * 검색
     */
    @GetMapping("/search")
    public SearchRes search(@RequestParam("keyword") String keyword, @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        return webtoonService.search(keyword, offset, limit);
    }

    /**
     * 웹툰 타입별 검색 (정식연재, 도전만화)
     */
    @GetMapping("/search/webtoon")
    public List<SearchWebtoonDto> search(@RequestParam("keyword") String keyword, @RequestParam("webtoonType")WebtoonType webtoonType, @RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        return webtoonService.searchAllByWebtoonType(keyword, webtoonType, offset, limit);
    }

    /**
     *  검색 결과 갯수 조회
     */
    @GetMapping("/search/count")
    public SearchCountRes searchCount(@RequestParam("keyword") String keyword) {
        return webtoonService.searchCount(keyword);
    }
}
