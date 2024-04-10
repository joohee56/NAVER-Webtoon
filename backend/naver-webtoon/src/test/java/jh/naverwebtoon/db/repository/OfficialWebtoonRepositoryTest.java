package jh.naverwebtoon.db.repository;

import java.time.DayOfWeek;
import java.util.List;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonByDayOfWeekRes;
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
        List<FindOfficialWebtoonByDayOfWeekRes> webtoons = repository.findAllByDayOfWeek(DayOfWeek.SATURDAY);
        for (FindOfficialWebtoonByDayOfWeekRes webtoon : webtoons) {
            System.out.println(webtoon.getWebtoonId() + ", " + webtoon.getWebtoonName() + ", " + webtoon.getThumbnail() + ", " + webtoon.getTotalLikeCount());
        }
    }

    @Test
    void 전쳬_웹툰_리스트_조회() {
        List<FindOfficialWebtoonsRes> webtoons = repository.findAll();
        for (FindOfficialWebtoonsRes webtoon : webtoons) {
            System.out.println(webtoon.toString());
        }
    }

    @Test
    void 테스트() {
        List<OfficialWebtoon> webtoons = repository.test();
        for (OfficialWebtoon webtoon : webtoons) {
            System.out.println(webtoon.toString());
        }
    }
}