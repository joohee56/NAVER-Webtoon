package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
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
}
