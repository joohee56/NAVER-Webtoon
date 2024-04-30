package jh.naverwebtoon.db.repository;

import java.util.List;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.response.FindWebtoonDetailRes;
import jh.naverwebtoon.dto.response.FindWebtoonsByMemberRes;
import jh.naverwebtoon.dto.response.SearchWebtoonDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WebtoonRepositoryTest {
    @Autowired
    private WebtoonRepository webtoonRepository;

    @Test
    void 내_웹툰_조회() {
        List<FindWebtoonsByMemberRes> webtoons = webtoonRepository.findAllByMemberWithThumbnail(
                Long.valueOf(2));
        for (FindWebtoonsByMemberRes webtoon : webtoons) {
            System.out.println(webtoon);
        }
    }

    @Test
    void 웹툰_조회_시_썸네일과_장르_함께_조회() {
        Webtoon webtoon = webtoonRepository.findOneByIdWithMemberAndThumbnail(Long.valueOf(2));
        FindWebtoonDetailRes res = FindWebtoonDetailRes.create(webtoon);
        System.out.println(res.toString());
    }

    @Test
    void 정식_연재_웹툰_검색() {
        int offset=0, limit = 5;
        List<SearchWebtoonDto> searchWebtoons = webtoonRepository.findAllByKeyword("세레나", WebtoonType.OFFICIAL, offset, limit);
        for (SearchWebtoonDto searchWebtoon : searchWebtoons) {
            System.out.println(searchWebtoon.toString());
        }
    }

    @Test
    void 도전_웹툰_검색() {
        int offset=0, limit = 5;
        List<SearchWebtoonDto> searchWebtoons = webtoonRepository.findAllByKeyword("딱지 간호사", WebtoonType.CHALLENGE, offset, limit);
        for (SearchWebtoonDto searchWebtoon : searchWebtoons) {
            System.out.println(searchWebtoon.toString());
        }
    }

    @Test
    void 검색_결과_횟수_조회() {
        Long count = webtoonRepository.findSearchCount("세레나", WebtoonType.OFFICIAL);
        System.out.println(count);
    }


}