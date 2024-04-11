package jh.naverwebtoon.service;

import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
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

    /**
     * 장르에 해당하는 전체 웹툰 리스트 조회
     * 인기순 / 업데이트 순으로 정렬
     */
    public List<FindOfficialWebtoonsRes> findOfficialWebtoonsByGenre(SortingEnum sorting, List<GenreEnum> genres) {
        if (sorting == SortingEnum.POPULARITY) {
            return webtoonGenreRepository.findOfficialWebtoonByGenreOrderByPopularity(genres);
        } else {
            return webtoonGenreRepository.findOfficialWebtoonByGenreUpdate(genres);
        }
    }
}
