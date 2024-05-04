package jh.naverwebtoon.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.repository.WebtoonGenreRepository;
import jh.naverwebtoon.dto.response.FindChallengeWebtoonsByGenreRes;
import jh.naverwebtoon.dto.response.WebtoonByGenreDto;
import jh.naverwebtoon.dto.response.FindChallengeWebtoonsRes;
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
     * 장르에 해당하는 전체 정식연재 웹툰 리스트 조회
     * 인기순 / 업데이트 순으로 정렬
     */
    public List<FindOfficialWebtoonsRes> findOfficialWebtoonsByGenre(SortingEnum sorting, List<GenreEnum> genres) {
        return webtoonGenreRepository.findOfficialWebtoonByGenre(genres, sorting);
    }

    /**
     * 전체 도전만화 조회 (페이징)
     * 장르별 웹툰, 전체 페이지 갯수
     */
    public FindChallengeWebtoonsRes findChallengeWebtoonAll(int offset, int limit, SortingEnum sorting) {
        Map<String, List<WebtoonByGenreDto>> webtoons = new HashMap<>();
        Long maxTotalCount = Long.valueOf(0);
        for (GenreEnum genreEnum : GenreEnum.values()) {
            webtoons.put(genreEnum.name(), webtoonGenreRepository.findChallengeWebtoonList(genreEnum, sorting, offset, limit));
            maxTotalCount = Math.max(maxTotalCount, webtoonGenreRepository.findTotalCountByGenre(genreEnum, WebtoonType.CHALLENGE));
        }
        int pageCount = maxTotalCount == 0 ? 1 : (int) Math.ceil((double)maxTotalCount / limit);
        System.out.println("MAX TOTAL COUNT= " + maxTotalCount);
        System.out.println("PAGE COUNT=" + pageCount);
        return new FindChallengeWebtoonsRes(webtoons, pageCount);
    }

    /**
     * 장르별 전체 도전만화 조회
     */
    public FindChallengeWebtoonsByGenreRes findChallengeWebtoonAllByGenre(GenreEnum genre, SortingEnum sorting, int offset, int limit) {
        List<WebtoonByGenreDto> webtoons = webtoonGenreRepository.findChallengeWebtoonList(genre, sorting, offset, limit);
        Long totalWebtoonCount = webtoonGenreRepository.findTotalCountByGenre(genre, WebtoonType.CHALLENGE);
        int pageCount = totalWebtoonCount == 0 ? 1 : (int) Math.ceil((double)totalWebtoonCount / limit);
        return new FindChallengeWebtoonsByGenreRes(webtoons, pageCount);
    }
}
