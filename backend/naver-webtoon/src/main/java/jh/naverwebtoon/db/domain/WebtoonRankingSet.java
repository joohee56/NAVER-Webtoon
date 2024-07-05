package jh.naverwebtoon.db.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebtoonRankingSet extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private WebtoonType webtoonType;

    public static WebtoonRankingSet create(WebtoonType webtoonType) {
        WebtoonRankingSet webtoonRankingSet = new WebtoonRankingSet();
        webtoonRankingSet.webtoonType = webtoonType;
        return webtoonRankingSet;
    }
}
