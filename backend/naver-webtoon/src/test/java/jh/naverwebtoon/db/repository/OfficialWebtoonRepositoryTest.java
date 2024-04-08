package jh.naverwebtoon.db.repository;

import java.util.ArrayList;
import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
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
    void 웹툰_리스트_조회() {
        List<GenreEnum> genres = new ArrayList<>();
        genres.add(GenreEnum.ROMANCE);
        List<OfficialWebtoon> webtoons = repository.findAll();
        for (OfficialWebtoon webtoon : webtoons) {
            System.out.println(webtoon.toString());
        }
    }
}