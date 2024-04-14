package jh.naverwebtoon.web.controller;

import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import jh.naverwebtoon.dto.response.FindChallengeWebtoonByGenre;
import jh.naverwebtoon.dto.response.FindChallengeWebtoonsRes;
import jh.naverwebtoon.dto.response.FindCreateRoundInfoRes;
import jh.naverwebtoon.dto.response.FindWebtoonsByMemberRes;
import jh.naverwebtoon.service.WebtoonGenreService;
import jh.naverwebtoon.service.WebtoonService;
import jh.naverwebtoon.web.Auth;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
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
public class WebtoonController {
    private final WebtoonService webtoonService;
    private final WebtoonGenreService webtoonGenreService;

    /**
     *  웹툰 등록
     */
    @Auth
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long createWebtoon(@Login Long id, @ModelAttribute CreateWebtoonReq createWebtoonReq) {
        Webtoon webtoon = webtoonService.createWebtoon(id, createWebtoonReq);
        return webtoon.getId();
    }

    /**
     * 회원이 갖고 있는 웹툰 정보 조회
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
    public List<FindChallengeWebtoonByGenre> findChallengeWebtoonAllByGenre(@PathVariable("genre") GenreEnum genre, @RequestParam("sorting") SortingEnum sorting) {
        return webtoonGenreService.findChallengeWebtoonAllByGenre(genre, sorting);
    }
}
