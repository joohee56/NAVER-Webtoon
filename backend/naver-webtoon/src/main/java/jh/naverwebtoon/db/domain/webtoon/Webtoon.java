package jh.naverwebtoon.db.domain.webtoon;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.BaseEntity;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.db.domain.Tag;
import jh.naverwebtoon.db.domain.WebtoonGenre;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.WebtoonCategory;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name = "serial", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("challenge")
public class Webtoon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "webtoon_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private WebtoonCategory webtoonCategory;

    @OneToMany(mappedBy = "webtoon",cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "webtoon", cascade = CascadeType.ALL)
    private List<WebtoonGenre> genres = new ArrayList<>();

    @Column(length = 30)
    private String oneLineSummary;

    @Column(length=1200)
    private String summary;

    private int ageLimit;

    private WebtoonType webtoonType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WebtoonThumbnail webtoonThumbnail;

    @OneToMany(mappedBy = "webtoon")
    private List<Round> rounds = new ArrayList<>();

    /**
     * 연관관계 메서드
     */
    private void addTag(Tag tag) {
        tags.add(tag);
        tag.setWebtoon(this);
    }
    private void addGenre(WebtoonGenre webtoonGenre) {
        genres.add(webtoonGenre);
        webtoonGenre.setWebtoon(this);
    }

    protected Webtoon(Member member, CreateWebtoonReq createWebtoonReq, List<Genre> genres, WebtoonThumbnail webtoonThumbnail, WebtoonType webtoonType) {
        this.name = createWebtoonReq.getName();
        this.webtoonCategory = createWebtoonReq.getWebtoonCategory();

        for (String tagName : createWebtoonReq.getTags()) {
            this.addTag(Tag.create(tagName));
        }

        List<WebtoonGenre> webtoonGenres = genres.stream()
                .map(genre -> WebtoonGenre.create(genre))
                .collect(Collectors.toList());
        for (WebtoonGenre webtoonGenre : webtoonGenres) {
            this.addGenre(webtoonGenre);
        }

        this.oneLineSummary = createWebtoonReq.getOneLineSummary();
        this.summary = createWebtoonReq.getSummary();
        this.ageLimit = 0;
        this.webtoonType = webtoonType;
        this.member = member;
        this.webtoonThumbnail = webtoonThumbnail;
    }

    public static Webtoon create(Member member, CreateWebtoonReq createWebtoonReq, List<Genre> genres, WebtoonThumbnail webtoonThumbnail) {
        Webtoon webtoon = new Webtoon();
        webtoon.name = createWebtoonReq.getName();
        webtoon.webtoonCategory = createWebtoonReq.getWebtoonCategory();

        for (String tagName : createWebtoonReq.getTags()) {
            webtoon.addTag(Tag.create(tagName));
        }

        List<WebtoonGenre> webtoonGenres = genres.stream()
                .map(genre -> WebtoonGenre.create(genre))
                .collect(Collectors.toList());
        for (WebtoonGenre webtoonGenre : webtoonGenres) {
            webtoon.addGenre(webtoonGenre);
        }

        webtoon.oneLineSummary = createWebtoonReq.getOneLineSummary();
        webtoon.summary = createWebtoonReq.getSummary();
        webtoon.webtoonType = WebtoonType.CHALLENGE;
        webtoon.member = member;
        webtoon.webtoonThumbnail = webtoonThumbnail;
        return webtoon;
    }


    @Override
    public String toString() {
        return "Webtoon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", webtoonThumbnail=" + webtoonThumbnail.getPosterImage().getStoreFileName() +
                '}';
    }
}
