package jh.naverwebtoon.db.repository;

import java.util.ArrayList;
import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.response.FindChallengeWebtoonByGenre;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes;
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
    void 장르별_웹툰_리스트_조회_인기순() {
        List<GenreEnum> genres = new ArrayList<>();
        genres.add(GenreEnum.ROMANCE);
        SortingEnum sorting = SortingEnum.POPULARITY;
        List<FindOfficialWebtoonsRes> webtoons = webtoonGenreRepository.findOfficialWebtoonByGenre(genres, sorting);
        for (FindOfficialWebtoonsRes webtoon : webtoons) {
            System.out.println(webtoon.toString());
        }
    }

    @Test
    void 장르별_웹툰_리스트_조회_업데이트순() {
        List<GenreEnum> genres = new ArrayList<>();
        genres.add(GenreEnum.ROMANCE);
        SortingEnum sorting = SortingEnum.UPDATE;
        List<FindOfficialWebtoonsRes> webtoons = webtoonGenreRepository.findOfficialWebtoonByGenre(genres, sorting);
        for (FindOfficialWebtoonsRes webtoon : webtoons) {
            System.out.println(webtoon.toString());
        }
    }

    @Test
    void 도전만화_조회() {
        List<FindChallengeWebtoonByGenre> challengeWebtoons = webtoonGenreRepository.findChallengeWebtoonList(GenreEnum.ROMANCE,
                SortingEnum.POPULARITY, 0, 5);
        for (FindChallengeWebtoonByGenre challengeWebtoon : challengeWebtoons) {
            System.out.println(challengeWebtoon.toString());
        }
    }

    @Test
    void 장르별_웹툰_갯수_조회() {
        Long totalCountByGenre = webtoonGenreRepository.findTotalCountByGenre(GenreEnum.ROMANCE, WebtoonType.CHALLENGE);
        System.out.println(totalCountByGenre);
    }
}