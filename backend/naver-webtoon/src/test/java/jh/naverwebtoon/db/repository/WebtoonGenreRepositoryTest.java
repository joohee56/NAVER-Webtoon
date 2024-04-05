package jh.naverwebtoon.db.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import jh.naverwebtoon.db.domain.Genre;
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
}