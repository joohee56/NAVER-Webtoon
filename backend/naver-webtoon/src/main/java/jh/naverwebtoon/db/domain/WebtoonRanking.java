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
import jh.naverwebtoon.db.domain.enums.RankingEnum;
import jh.naverwebtoon.db.domain.enums.RankingStatus;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebtoonRanking extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;

    @Enumerated(EnumType.ORDINAL)
    private RankingEnum ranking;

    @Enumerated(EnumType.STRING)
    private RankingStatus status;

    private Long totalLikeCount;

    public static WebtoonRanking create(Webtoon webtoon, int ranking, RankingStatus rankingStatus, Long totalLikeCount) {
        WebtoonRanking webtoonRanking = new WebtoonRanking();
        webtoonRanking.webtoon = webtoon;
        webtoonRanking.ranking = RankingEnum.create(ranking);
        webtoonRanking.status = rankingStatus;
        webtoonRanking.totalLikeCount = totalLikeCount;
        return webtoonRanking;
    }
}
