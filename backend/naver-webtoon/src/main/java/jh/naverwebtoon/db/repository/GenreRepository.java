package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import org.springframework.stereotype.Repository;

@Repository
public class GenreRepository {
    @PersistenceContext
    private EntityManager em;

    public Genre findByGenreEnum(GenreEnum genreEnum) {
        return (Genre) em.createQuery("select g from Genre g where g.genreEnum = :genreEnum")
                .setParameter("genreEnum", genreEnum)
                .getSingleResult();
    }
}
