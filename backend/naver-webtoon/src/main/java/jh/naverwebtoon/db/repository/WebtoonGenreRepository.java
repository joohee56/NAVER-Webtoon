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
     * 최근 업데이트 순서대로 정렬
     */
    public List<FindOfficialWebtoonsRes> findOfficialWebtoonByGenreUpdate(List<GenreEnum> genres) {
        return em.createQuery("select distinct new jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes(wg.webtoon"
                + ", (select count(r) from Round r where function('date_format', r.createdAt, \"%Y-%m-%d\") = current_date() and r.webtoon = wg.webtoon) as roundUpdateCount"
                + ", null"
                + ", (select r.createdAt from Round r where r.webtoon=wg.webtoon order by r.createdAt desc limit 1) as updatedAt"
                + ") from WebtoonGenre wg"
                + " join fetch wg.webtoon.webtoonThumbnail wt"
                + " where wg.webtoon.webtoonType=:webtoonType"
                + " and wg.genre.genreEnum in :genres"
                + " order by updatedAt desc", FindOfficialWebtoonsRes.class)
                .setParameter("genres", genres)
                .setParameter("webtoonType", WebtoonType.OFFICIAL)
                .getResultList();
    }

    /**
     * 장르별 전체 웹툰 리스트 조회 (웹툰 정보 + 오늘 날짜에 업로드된 회차의 갯수)
     * 인기순으로 정렬
     */
    public List<FindOfficialWebtoonsRes> findOfficialWebtoonByGenreOrderByPopularity(List<GenreEnum> genres) {
        return em.createQuery("select distinct new jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes(wg.webtoon"
                        + ", (select count(r) from Round r where function('date_format', r.createdAt, \"%Y-%m-%d\") = current_date() and r.webtoon = wg.webtoon) as roundUpdateCount"
                        + ", (select count(rl) as likeCount from RoundLike rl where rl.round.id in (select roundId from (select r.id as roundId from Round r where r.webtoon=wg.webtoon order by r.createdAt desc limit 10) as sub)) as totalLikeCount"
                        + ", null"
                        + ") from WebtoonGenre wg"
                        + " join fetch wg.webtoon.webtoonThumbnail wt"
                        + " where wg.webtoon.webtoonType=:webtoonType"
                        + " and wg.genre.genreEnum in :genres"
                        + " order by totalLikeCount desc", FindOfficialWebtoonsRes.class)
                .setParameter("genres", genres)
                .setParameter("webtoonType", WebtoonType.OFFICIAL)
                .getResultList();
    }
}
