package jh.naverwebtoon.db.domain.webtoon;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
import jh.naverwebtoon.db.domain.Tag;
import jh.naverwebtoon.db.domain.WebtoonGenre;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public class Webtoon extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "webtoon_id")
    private Long id;

    @Column(name = "webtoon_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private WebtoonType webtoonType;

    @OneToMany(mappedBy = "webtoon",cascade = CascadeType.ALL)
    private List<Tag> tags = new ArrayList<>();

    @OneToMany(mappedBy = "webtoon", cascade = CascadeType.ALL)
    private List<WebtoonGenre> genres = new ArrayList<>();

    @Column(length = 30)
    private String oneLineSummary;

    @Column(length=1200)
    private String summary;

    private int ageLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private WebtoonThumbnail webtoonThumbnail;

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

    public static Webtoon createWebtoon(Member member, CreateWebtoonReq createWebtoonReq, List<WebtoonGenre> webtoonGenres, WebtoonThumbnail webtoonThumbnail) {
        Webtoon webtoon = new Webtoon();
        webtoon.name = createWebtoonReq.getName();
        webtoon.webtoonType = createWebtoonReq.getWebtoonType();
        for (String tagName : createWebtoonReq.getTags()) {
            webtoon.addTag(Tag.create(tagName));
        }
        for (WebtoonGenre webtoonGenre : webtoonGenres) {
            webtoon.addGenre(webtoonGenre);
        }
        webtoon.oneLineSummary = createWebtoonReq.getOneLineSummary();
        webtoon.summary = createWebtoonReq.getSummary();
        webtoon.member = member;
        webtoon.webtoonThumbnail = webtoonThumbnail;
        return webtoon;
    }

}
