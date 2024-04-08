package jh.naverwebtoon.db.repository;

import java.util.ArrayList;
import java.util.List;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WebtoonGenreRepositoryTest {
    @Autowired
    private WebtoonGenreRepository webtoonGenreRepository;

    @Test
    void 웹툰_장르_조회() {
        List<Genre> genres = webtoonGenreRepository.findGenreByWebtoonId(Long.valueOf(1));
        for (Genre genre : genres) {
            System.out.println(genre.getGenreEnum().getTitle());
        }
    }

    @Test
    void 장르별_웹툰_조회() {
        List<GenreEnum> genres = new ArrayList<>();
        genres.add(GenreEnum.ROMANCE);
        genres.add(GenreEnum.DAILY_LIFE);
        List<OfficialWebtoon> webtoons = webtoonGenreRepository.findOfficialWebtoonByGenre(genres);
        for (OfficialWebtoon webtoon : webtoons) {
            System.out.println(webtoon.getId() + ", " + webtoon.getName() + ", " + webtoon.getWebtoonThumbnail().getPosterImage().getStoreFileName() + ", " + webtoon.getDayOfWeek() + ", " + webtoon.isComplete());
        }
    }
}