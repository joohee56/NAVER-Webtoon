package jh.naverwebtoon.db.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import lombok.Getter;

@Entity
@Getter
public class Genre {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GenreEnum genreEnum;

    public static Genre create(GenreEnum genreEnum) {
        Genre genre = new Genre();
        genre.genreEnum = genreEnum;
        return genre;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", genreEnum=" + genreEnum +
                '}';
    }
}
