package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class RoundRepository {

    @PersistenceContext
    private EntityManager em;

    public Long findMaxRoundNumberByWebtoonId(Long webtoonId) {
        return em.createQuery("select max(r.roundNumber) from Round r where r.webtoon.id = :webtoonId", Long.class)
                .setParameter("webtoonId", webtoonId)
                .getSingleResult();
    }
}
