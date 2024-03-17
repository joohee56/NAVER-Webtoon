package jh.naverwebtoon.web.controller;

import java.io.IOException;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import jh.naverwebtoon.dto.response.CreateWebtoonRes;
import jh.naverwebtoon.service.WebtoonService;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public CreateWebtoonRes createWebtoon(@Login Long id, @ModelAttribute CreateWebtoonReq createWebtoonReq)
            throws IOException {
        Webtoon webtoon = webtoonService.createWebtoon(id, createWebtoonReq);
        return CreateWebtoonRes.create(webtoon);
    }
}
