package jh.naverwebtoon.db.domain.webtoon;

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
import jh.naverwebtoon.db.domain.BaseEntity;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.enums.GENRE;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
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

    @Enumerated(EnumType.STRING)
    private GENRE genre;

    @Column(length = 30)
    private String oneLineSummary;

    @Column(length=1200)
    private String summary;

    private int ageLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
