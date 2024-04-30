package jh.naverwebtoon.service;

import static org.junit.jupiter.api.Assertions.*;

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
}