package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WebtoonRepository {
    @PersistenceContext
    private EntityManager em;
    private final MemberRepository memberRepository;
    private final GenreRepository genreRepository;

    public Long save(Webtoon webtoon) {
        em.persist(webtoon);
        return webtoon.getId();
    }

    public Webtoon findOne(Long id) {
        return em.find(Webtoon.class, id);
    }

    public List<Webtoon> findAllByMember(Member member) {
        return em.createQuery("select distinct w from Webtoon w"
                        + " join fetch w.webtoonThumbnail wt"
                        + " where w.member = :member", Webtoon.class)
                .setParameter("member", member)
                .getResultList();
    }
}
