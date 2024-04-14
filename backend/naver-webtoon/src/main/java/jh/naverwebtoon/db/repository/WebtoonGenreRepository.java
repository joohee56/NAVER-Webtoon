package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.response.FindChallengeWebtoonByGenre;
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
     * 도전웹툰 장르별 웹툰 조회 (페이징)
     * 인기순 / 업데이트순 정렬
     */
    public List<FindChallengeWebtoonByGenre> findChallengeWebtoonList(GenreEnum genre, SortingEnum sorting, int offset, int limit) {
        String sql = "select distinct new jh.naverwebtoon.dto.response.FindChallengeWebtoonByGenre(wg.webtoon"
                + ", (select count(r) from Round r where function('date_format', r.createdAt, \"%Y-%m-%d\") = current_date() and r.webtoon = wg.webtoon) as roundUpdateCount"  //회차 업데이트 여부
                + ", (select count(rl) as likeCount from RoundLike rl where rl.round.id in (select roundId from (select r.id as roundId from Round r where r.webtoon=wg.webtoon order by r.createdAt desc limit 10) as sub)) as totalLikeCount"
                + ", (select max(r.createdAt) from Round r where r.webtoon=wg.webtoon) as updatedAt"
                + ") from WebtoonGenre wg"
                + " join fetch wg.webtoon.webtoonThumbnail wt"
                + " where wg.webtoon.webtoonType=:webtoonType"
                + " and wg.genre.genreEnum=:genre";
        if (sorting == SortingEnum.POPULARITY) {
            sql += " order by totalLikeCount desc";
        } else {
            sql += " order by updatedAt desc";
        }

        return em.createQuery(sql, FindChallengeWebtoonByGenre.class)
                .setParameter("genre", genre)
                .setParameter("webtoonType", WebtoonType.CHALLENGE)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }


    /**
     * 정식 연재 웹툰 장르별 전체 리스트 조회 (웹툰 정보 + 오늘 날짜에 업로드된 회차의 갯수 + 총 좋아요 갯수)
     * 인기순 / 업데이트순 정렬
     */
    public List<FindOfficialWebtoonsRes> findOfficialWebtoonByGenre(List<GenreEnum> genres, SortingEnum sorting) {
        String sql = "select distinct new jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes(wg.webtoon"
                + ", (select count(r) from Round r where function('date_format', r.createdAt, \"%Y-%m-%d\") = current_date() and r.webtoon = wg.webtoon) as roundUpdateCount"
                + ", (select count(rl) as likeCount from RoundLike rl where rl.round.id in (select roundId from (select r.id as roundId from Round r where r.webtoon=wg.webtoon order by r.createdAt desc limit 10) as sub)) as totalLikeCount"
                + ", (select max(r.createdAt) from Round r where r.webtoon=wg.webtoon) as updatedAt"
                + ") from WebtoonGenre wg"
                + " join fetch wg.webtoon.webtoonThumbnail wt"
                + " where wg.webtoon.webtoonType=:webtoonType"
                + " and wg.genre.genreEnum in :genres";

        if (sorting == SortingEnum.POPULARITY) {
            sql += " order by totalLikeCount desc";
        } else {
            sql += " order by updatedAt desc";
        }

        return em.createQuery(sql, FindOfficialWebtoonsRes.class)
                .setParameter("genres", genres)
                .setParameter("webtoonType", WebtoonType.OFFICIAL)
                .getResultList();
    }

    /**
     * 장르별 전체 웹툰 갯수
     */
    public Long findTotalCountByGenre(GenreEnum genre, WebtoonType webtoonType) {
        return em.createQuery("select count(wg) from WebtoonGenre wg"
                + " where wg.genre.genreEnum=:genre"
                + " and wg.webtoon.webtoonType=:webtoonType", Long.class)
                .setParameter("genre", genre)
                .setParameter("webtoonType", webtoonType)
                .getSingleResult();
    }
}
