package jh.naverwebtoon.service;

import jh.naverwebtoon.dto.response.FindEditWebtoonRes;
import jh.naverwebtoon.dto.response.SearchRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WebtoonServiceTest {
    @Autowired
    private WebtoonService webtoonService;

    @Test
    void 검색() {
        SearchRes searchRes = webtoonService.search("세레나", 0, 5);
        System.out.println(searchRes);
    }

    @Test
    void 수정할_웹툰_정보_조회() {
        FindEditWebtoonRes editWebtoonInfo = webtoonService.findEditWebtoonInfo(Long.valueOf(2));
        System.out.println(editWebtoonInfo.toString());
    }
}