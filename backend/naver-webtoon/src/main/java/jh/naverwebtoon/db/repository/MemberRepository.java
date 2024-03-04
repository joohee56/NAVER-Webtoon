package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jh.naverwebtoon.db.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    @PersistenceContext
    EntityManager em;

    public Long save(Member m) {
        em.persist(m);
        return m.getId();
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
}
