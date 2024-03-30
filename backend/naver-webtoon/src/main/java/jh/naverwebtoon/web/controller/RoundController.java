package jh.naverwebtoon.web.controller;

import java.util.List;
import jh.naverwebtoon.dto.request.CreateRoundReq;
import jh.naverwebtoon.dto.response.FindRoundDetailRes;
import jh.naverwebtoon.dto.response.FindRoundsByWebtoon;
import jh.naverwebtoon.dto.response.FindRoundsByWebtoonWithPaging;
import jh.naverwebtoon.service.RoundService;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    /**
     * 회차 등록
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long saveRond(@Login Long memberId, @ModelAttribute CreateRoundReq createRoundReq) {
       return roundService.save(memberId, createRoundReq);
    }
}
