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
import jh.naverwebtoon.db.domain.enums.RankingStatus;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebtoonRanking extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_raking_set_id")
    private WebtoonRankingSet webtoonRankingSet;  // 랭킹 조회 시 한번에 가져와야할 웹툰을 묶어주는 역할

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "webtoon_id")
    private Webtoon webtoon;

    private int rankingNo;

    @Enumerated(EnumType.STRING)
    private RankingStatus status;

    private Long totalLikeCount;

    public static WebtoonRanking create(Webtoon webtoon, int rankingNo, RankingStatus status, Long totalLikeCount, WebtoonRankingSet webtoonRankingSet) {
        WebtoonRanking webtoonRanking = new WebtoonRanking();
        webtoonRanking.webtoonRankingSet = webtoonRankingSet;
        webtoonRanking.webtoon = webtoon;
        webtoonRanking.rankingNo = rankingNo;
        webtoonRanking.status = status;
        webtoonRanking.totalLikeCount = totalLikeCount;
        return webtoonRanking;
    }
}
