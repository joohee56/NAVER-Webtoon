package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenreRepository {
    @PersistenceContext
    private EntityManager em;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initGenre() {
        for (GenreEnum genreEnum : GenreEnum.values()) {
            Genre genre = Genre.create(genreEnum);
            em.persist(genre);
        }
    }

    public Genre findByGenreEnum(GenreEnum genreEnum) {
        return (Genre) em.createQuery("select g from Genre g where g.genreEnum = :genreEnum")
                .setParameter("genreEnum", genreEnum)
                .getSingleResult();
    }
}
