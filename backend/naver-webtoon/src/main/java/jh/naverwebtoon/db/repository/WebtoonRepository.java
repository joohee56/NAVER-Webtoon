package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WebtoonRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Webtoon webtoon) {
        em.persist(webtoon);
        return webtoon.getId();
    }

    public Webtoon findOne(Long id) {
        return em.find(Webtoon.class, id);
    }

    public List<Webtoon> findAllByMemberWithThumbnail(Long memberId) {
        return em.createQuery("select distinct w from Webtoon w"
                        + " join fetch w.webtoonThumbnail wt"
                        + " where w.member.id = :memberId", Webtoon.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<Object[]> findAllByMemberWithMaxRoundNumber(Long memberId) {
        return em.createQuery(
                        "select distinct w.id, w.name, (select max(r.roundNumber) from Round r where r.webtoon = w)"
                                + " from Webtoon w"
                                + " cross join Round r"
                                + " where w.member.id = :memberId")
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
