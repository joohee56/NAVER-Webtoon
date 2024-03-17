package jh.naverwebtoon.db.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebtoonGenre {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "webtoon_genre_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public void setWebtoon(Webtoon webtoon) {
        this.webtoon = webtoon;
    }

    public static WebtoonGenre create(Genre genre) {
        WebtoonGenre webtoonGenre = new WebtoonGenre();
        webtoonGenre.genre = genre;
        return webtoonGenre;
    }
}
