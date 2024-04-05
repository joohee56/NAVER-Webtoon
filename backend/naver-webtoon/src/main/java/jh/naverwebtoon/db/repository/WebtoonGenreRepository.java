package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WebtoonGenreRepository {
    private final EntityManager em;

    public List<Genre> findGenreByWebtoonId(Long webtoonId) {
        return em.createQuery("select wg.genre from WebtoonGenre wg"
                + " where wg.webtoon.id=:webtoonId", Genre.class)
                .setParameter("webtoonId", webtoonId)
                .getResultList();
    }
}
