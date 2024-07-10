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
import jh.naverwebtoon.db.domain.BaseEntity;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.db.domain.Tag;
import jh.naverwebtoon.db.domain.WebtoonGenre;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonCategory;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.request.CreateOfficialWebtoonReq;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import jh.naverwebtoon.dto.request.EditWebtoonReq;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "webtoon_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private WebtoonCategory webtoonCategory;

    @OneToMany(mappedBy = "webtoon",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tag> tags = new ArrayList<>();

    @BatchSize(size = 5)
    @OneToMany(mappedBy = "webtoon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WebtoonGenre> genres = new ArrayList<>();

    @Column(length = 30)
    private String oneLineSummary;

    @Column(length=1200)
    private String summary;

    private int ageLimit;

    @Enumerated(EnumType.STRING)
    private WebtoonType webtoonType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WebtoonThumbnail webtoonThumbnail;

    @OneToMany(mappedBy = "webtoon")
    private List<Round> rounds = new ArrayList<>();

    //== 연관관계 메서드 ==/
    private void addTag(Tag tag) {
        tags.add(tag);
        tag.setWebtoon(this);
    }
    //== 연관관계 메서드 ==/
    private void addGenre(WebtoonGenre genre) {
        this.genres.add(genre);
        genre.setWebtoon(this);
    }

    protected Webtoon(Member member, CreateOfficialWebtoonReq createOfficialWebtoonReq, WebtoonThumbnail webtoonThumbnail) {
        this.name = createOfficialWebtoonReq.getName();
        this.webtoonCategory = createOfficialWebtoonReq.getWebtoonCategory();
        for (String tagName : createOfficialWebtoonReq.getTags()) {
            this.addTag(Tag.create(tagName));
        }
        for (GenreEnum genreEnum : createOfficialWebtoonReq.getGenres()) {
            addGenre(WebtoonGenre.create(genreEnum));
        }
        this.oneLineSummary = createOfficialWebtoonReq.getOneLineSummary();
        this.summary = createOfficialWebtoonReq.getSummary();
        this.ageLimit = createOfficialWebtoonReq.getAgeLimit();
        this.webtoonType = WebtoonType.OFFICIAL;
        this.member = member;
        this.webtoonThumbnail = webtoonThumbnail;
    }

    public static Webtoon create(Member member, CreateWebtoonReq createWebtoonReq, WebtoonThumbnail webtoonThumbnail) {
        Webtoon webtoon = new Webtoon();
        webtoon.name = createWebtoonReq.getName();
        webtoon.webtoonCategory = createWebtoonReq.getWebtoonCategory();
        for (String tagName : createWebtoonReq.getTags()) {
            webtoon.addTag(Tag.create(tagName));
        }
        for (GenreEnum genreEnum : createWebtoonReq.getGenres()) {
            webtoon.addGenre(WebtoonGenre.create(genreEnum));
        }
        webtoon.oneLineSummary = createWebtoonReq.getOneLineSummary();
        webtoon.summary = createWebtoonReq.getSummary();
        webtoon.webtoonType = WebtoonType.CHALLENGE;
        webtoon.member = member;
        webtoon.webtoonThumbnail = webtoonThumbnail;
        return webtoon;
    }

    public void edit(EditWebtoonReq editWebtoonReq) {
        this.name = editWebtoonReq.getName();
        this.webtoonCategory = editWebtoonReq.getWebtoonCategory();

        this.genres = new ArrayList<>();
        for (GenreEnum genreEnum : editWebtoonReq.getGenres()) {
            this.addGenre(WebtoonGenre.create(genreEnum));
        }
        this.oneLineSummary = editWebtoonReq.getOneLineSummary();
        this.summary = editWebtoonReq.getSummary();
    }
}
