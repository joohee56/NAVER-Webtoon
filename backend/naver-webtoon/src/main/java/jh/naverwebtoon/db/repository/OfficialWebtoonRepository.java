package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.time.DayOfWeek;
import java.util.List;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes;
import jh.naverwebtoon.dto.response.FindWebtoonsByCondition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OfficialWebtoonRepository {
    private final EntityManager em;

    public Long save(OfficialWebtoon officialWebtoon) {
        em.persist(officialWebtoon);
        return officialWebtoon.getId();
    }

    public OfficialWebtoon findOne(Long id) {
        return em.find(OfficialWebtoon.class, id);
    }

    /**
     * 전체 웹툰 리스트 조회 (웹툰 정보 + 오늘 날짜에 업로드된 회차의 갯수 + 업데이트된 날짜)
     * 최근 업데이트 순으로 정렬
     */
    public List<FindOfficialWebtoonsRes> findAllOrderByUpdate() {
        return em.createQuery("select new jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes(ow"
                        + ", (select count(r) from Round r where function('date_format', r.createdAt, \"%Y-%m-%d\") = current_date() and r.webtoon = ow)"
                        + ", null"
                        + ", (select r.createdAt from Round r where r.webtoon=ow order by r.createdAt desc limit 1) as updatedAt"
                        + ") from OfficialWebtoon ow"
                        + " join fetch ow.webtoonThumbnail wt"
                        + " order by updatedAt desc", FindOfficialWebtoonsRes.class)
                .getResultList();
    }

    /**
     * 전체 웹툰 리스트 조회 (웹툰 정보 + 오늘 날짜에 업로드된 회차의 갯수 + 총 좋아요 갯수)
     * 인기순으로 정렬
     */
    public List<FindOfficialWebtoonsRes> findAllOrderByPopularity() {
        return em.createQuery("select new jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes(ow"
                        + ", (select count(r) from Round r where function('date_format', r.createdAt, \"%Y-%m-%d\") = current_date() and r.webtoon = ow)"
                        + ", (select count(rl) as likeCount from RoundLike rl where rl.round.id in (select roundId from (select r.id as roundId from Round r where r.webtoon=ow order by r.createdAt desc limit 10) as sub)) as totalLikeCount"
                        + ", null"
                        + ") from OfficialWebtoon ow"
                        + " join fetch ow.webtoonThumbnail wt"
                        + " where ow.webtoonType=:webtoonType"
                        + " order by totalLikeCount desc", FindOfficialWebtoonsRes.class)
                        .setParameter("webtoonType", WebtoonType.OFFICIAL)
                        .getResultList();
    }

    /**
     * 요일별 웹툰 리스트 조회 (웹툰 정보, 최근 등록된 10개 회차의 누적 좋아요 수, 오늘 날짜에 업로드된 회차의 갯수)
     */
    public List<FindWebtoonsByCondition> findAllByDayOfWeek(DayOfWeek dayOfWeek, SortingEnum sorting) {
        String sql = "select new jh.naverwebtoon.dto.response.FindWebtoonsByCondition"
                + "(ow.id, ow.name, ow.webtoonThumbnail.posterImage.storeFileName"
                + ", (select count(rl) as likeCount from RoundLike rl where rl.round.id in (select roundId from (select r.id as roundId from Round r where r.webtoon=ow order by r.createdAt desc limit 10))) as totalLikeCount"
                + ", (select count(r) from Round r where function('date_format', r.createdAt, \"%Y-%m-%d\") = current_date() and r.webtoon = ow)) from OfficialWebtoon ow"
                + " where ow.dayOfWeek=:dayOfWeek";

        if (sorting == SortingEnum.POPULARITY) {
            sql += " order by totalLikeCount desc";
        } else {
            sql += " order by (select max(r.createdAt) from Round r where r.webtoon=ow) desc";
        }

        return em.createQuery(sql, FindWebtoonsByCondition.class)
                .setParameter("dayOfWeek", dayOfWeek)
                .getResultList();
    }
}
