package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.response.FindWebtoonsByMemberRes;
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

    public Webtoon findOneWithThumbnail(Long webtoonId) {
        return em.createQuery("select w from Webtoon w"
                + " join fetch w.webtoonThumbnail wt"
                + " where w.id = :webtoonId", Webtoon.class)
                .setParameter("webtoonId", webtoonId)
                .getSingleResult();
    }

    public List<FindWebtoonsByMemberRes> findAllByMemberWithThumbnail(Long memberId) {
        return em.createQuery("select new FindWebtoonsByMemberRes(w.id, w.name, w.webtoonThumbnail.posterImage.storeFileName, w.webtoonType, (select count(c) from Comment c where c.round.webtoon=w)) from Webtoon w"
                        + " where w.member.id = :memberId", FindWebtoonsByMemberRes.class)
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
