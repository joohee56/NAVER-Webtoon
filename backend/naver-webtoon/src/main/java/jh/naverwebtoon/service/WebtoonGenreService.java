package jh.naverwebtoon.service;

import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.repository.WebtoonGenreRepository;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WebtoonGenreService {
    private final WebtoonGenreRepository webtoonGenreRepository;

    public List<FindOfficialWebtoonsRes> findOfficialWebtoonsByGenre(List<GenreEnum> genres) {
        return webtoonGenreRepository.findOfficialWebtoonByGenre(genres);
    }
}
