package jh.naverwebtoon.db.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class WebtoonGenre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    public void setWebtoon(Webtoon webtoon) {
        this.webtoon = webtoon;
    }

    public static WebtoonGenre create(GenreEnum genre) {
        WebtoonGenre webtoonGenre = new WebtoonGenre();
        webtoonGenre.genre = genre;
        return webtoonGenre;
    }
}
