package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WebtoonGenreRepository {
    private final EntityManager em;

    public List<Genre> findGenreByWebtoonId(Long webtoonId) {
        return em.createQuery("select wg.genre from WebtoonGenre wg"
                + " where wg.webtoon.id=:webtoonId", Genre.class)
                .setParameter("webtoonId", webtoonId)
                .getResultList();
    }

    /**
     * 장르별 전체 웹툰 리스트 조회 (웹툰 정보 + 오늘 날짜에 업로드된 회차의 갯수)
     */
    public List<FindOfficialWebtoonsRes> findOfficialWebtoonByGenre(List<GenreEnum> genres) {
        return em.createQuery("select new jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes(wg.webtoon"
                + ", (select count(r) from Round r where function('date_format', r.createdAt, \"%Y-%m-%d\") = current_date() and r.webtoon = wg.webtoon) as roundUpdateCount"
                + ") from WebtoonGenre wg"
                + " join fetch wg.webtoon.webtoonThumbnail wt"
                + " where wg.webtoon.webtoonType=:webtoonType"
                + " and wg.genre.genreEnum in :genres", FindOfficialWebtoonsRes.class)
                .setParameter("genres", genres)
                .setParameter("webtoonType", WebtoonType.OFFICIAL)
                .getResultList();
    }
}
