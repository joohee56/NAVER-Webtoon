package jh.naverwebtoon.web.controller;

import java.io.IOException;
import java.util.List;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import jh.naverwebtoon.dto.response.FindWebtoonsByMemberRes;
import jh.naverwebtoon.service.WebtoonService;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/webtoon")
public class WebtoonController {
    private final WebtoonService webtoonService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Long createWebtoon(@Login Long id, @ModelAttribute CreateWebtoonReq createWebtoonReq)
            throws IOException {
        Webtoon webtoon = webtoonService.createWebtoon(id, createWebtoonReq);
        return webtoon.getId();
    }

    @GetMapping()
    public List<FindWebtoonsByMemberRes> findAllByMember(@Login Long id) {
        return webtoonService.findAllByMember(id);
    }

}
