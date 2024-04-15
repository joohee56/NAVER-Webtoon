package jh.naverwebtoon.db.repository;

import java.time.DayOfWeek;
import java.util.List;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
import jh.naverwebtoon.dto.response.FindWebtoonsByCondition;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OfficialWebtoonRepositoryTest {
    @Autowired
    private OfficialWebtoonRepository repository;

    @Test
    void 요일별_웹툰_리스트_조회() {
        List<FindWebtoonsByCondition> webtoons = repository.findAllByDayOfWeek(DayOfWeek.MONDAY, SortingEnum.POPULARITY);
        for (FindWebtoonsByCondition webtoon : webtoons) {
            System.out.println(webtoon.getWebtoonId() + ", " + webtoon.getWebtoonName() + ", " + webtoon.getPosterStoreFileName() + ", " + webtoon.getTotalLikeCount());
        }
    }

    @Test
    void 전쳬_웹툰_리스트_조회_업데이트순() {
        List<FindOfficialWebtoonsRes> webtoons = repository.findAllOrderByUpdate();
        for (FindOfficialWebtoonsRes webtoon : webtoons) {
            System.out.println(webtoon.toString());
        }
    }

    @Test
    void 전쳬_웹툰_리스트_조회_인기순() {
        List<FindOfficialWebtoonsRes> webtoons = repository.findAllOrderByPopularity();
        for (FindOfficialWebtoonsRes webtoon : webtoons) {
            System.out.println(webtoon.toString());
        }
    }

}