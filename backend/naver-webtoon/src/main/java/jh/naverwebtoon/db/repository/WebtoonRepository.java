package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import org.springframework.stereotype.Repository;

@Repository
public class WebtoonRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Webtoon webtoon) {
        em.persist(webtoon);
        return webtoon.getId();
    }

    public Webtoon findByOne(Long id) {
        return em.find(Webtoon.class, id);
    }
}
