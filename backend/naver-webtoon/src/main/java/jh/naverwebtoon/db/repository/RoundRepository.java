package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jh.naverwebtoon.db.domain.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class RoundRepository {

    @PersistenceContext
    private EntityManager em;

    public Round findOne(Long roundId) {
        return em.find(Round.class, roundId);
    }

    public List<Round> findAllByWebtoonWithPaging(Long webtoonId, int offset, int limit, boolean isDesc) {
        String sql = "select r from Round r"
                + " join fetch r.roundThumbnail th"
                + " where r.webtoon.id = :webtoonId";
        if (isDesc) {
            sql += " order by r.roundNumber desc";
        } else {
            sql += " order by r.roundNumber asc";
        }

        return em.createQuery(sql, Round.class)
                .setParameter("webtoonId", webtoonId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Round> findAllByWebtoon(Long webtoonId) {
        return em.createQuery("select r from Round r"
                + " join fetch r.roundThumbnail th"
                + " where r.webtoon.id = :webtoonId")
                .setParameter("webtoonId",webtoonId)
                .getResultList();
    }

    /**
     * 웹툰에 해당하는 전체 회차 갯수 조회
     */
    public Long findTotalCountByWebtoon(Long webtoonId) {
        return em.createQuery("select count(r) from Round r where r.webtoon.id = :webtoonId", Long.class)
                .setParameter("webtoonId", webtoonId)
                .getSingleResult();
    }

    public Long findMaxRoundNumberByWebtoonId(Long webtoonId) {
        return em.createQuery("select max(r.roundNumber) from Round r where r.webtoon.id = :webtoonId", Long.class)
                .setParameter("webtoonId", webtoonId)
                .getSingleResult();
    }

    public Round findOneDetail(Long id) {
        return em.createQuery("select r from Round r"
                + " join fetch r.webtoon w"
                + " join fetch w.member m"
                + " join fetch m.profileImage"
                + " join fetch r.mergeManuscript"
                + " where r.id=:id", Round.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
