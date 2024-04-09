package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.time.DayOfWeek;
import java.util.List;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonByDayOfWeekRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OfficialWebtoonRepository {
    private final EntityManager em;

    public void save(OfficialWebtoon officialWebtoon) {
        em.persist(officialWebtoon);
    }

    public OfficialWebtoon findOne(Long id) {
        return em.find(OfficialWebtoon.class, id);
    }

    public List<OfficialWebtoon> findAll() {
        return em.createQuery("select ow from OfficialWebtoon ow"
                        + " join fetch ow.webtoonThumbnail wt", OfficialWebtoon.class)
                .getResultList();
    }

    public OfficialWebtoon findOneByIdWithMemberAndThumbnail(Long webtoonId) {
        return em.createQuery("select distinct ow from OfficialWebtoon ow"
                        + " join fetch ow.member m "
                        + " join fetch m.profileImage"
                        + " join fetch ow.webtoonThumbnail wt"
                        + " where ow.id = :webtoonId", OfficialWebtoon.class)
                .setParameter("webtoonId", webtoonId )
                .getSingleResult();
    }

    /**
     * 요일별 웹툰 리스트 조회 (웹툰 정보, 최근 등록된 10개 회차의 누적 좋아요 수)
     */
    public List<FindOfficialWebtoonByDayOfWeekRes> findAllByDayOfWeek(DayOfWeek dayOfWeek) {
        return em.createQuery("select new jh.naverwebtoon.dto.response.FindOfficialWebtoonByDayOfWeekRes(ow.id, ow.name, ow.webtoonThumbnail.posterImage.storeFileName, ow.dayOfWeek, (select count(rl) as likeCount from RoundLike rl where rl.round.id in (select roundId from (select r.id as roundId from Round r where r.webtoon=ow order by r.createdAt desc limit 10)))) from OfficialWebtoon ow"
                + " where ow.dayOfWeek=:dayOfWeek", FindOfficialWebtoonByDayOfWeekRes.class)
                .setParameter("dayOfWeek", dayOfWeek)
                .getResultList();
    }
}
