package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
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

    public List<OfficialWebtoon> findOfficialWebtoonByGenre(List<GenreEnum> genres) {
        return em.createQuery("select wg.webtoon from WebtoonGenre wg"
                + " join fetch wg.webtoon.webtoonThumbnail wt"
                + " where wg.webtoon.webtoonType=:webtoonType"
                + " and wg.genre.genreEnum in :genres", OfficialWebtoon.class)
                .setParameter("genres", genres)
                .setParameter("webtoonType", WebtoonType.OFFICIAL)
                .getResultList();
    }
}
