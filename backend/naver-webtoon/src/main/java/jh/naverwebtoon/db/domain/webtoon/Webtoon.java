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
import jh.naverwebtoon.db.domain.enums.GENRE;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.enums.WebtoonType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Webtoon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "webtoon_id")
    private Long id;

    @Column(name = "webtoon_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WebtoonType webtoonType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GENRE genre;

    @Column(nullable = false, length = 30)
    private String oneLineSummary;

    @Column(nullable = false, length=1200)
    private String summary;

    @Column(nullable = false)
    private int ageLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
