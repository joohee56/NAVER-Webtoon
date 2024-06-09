package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jh.naverwebtoon.db.domain.WebtoonRankingSet;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WebtoonRankingSetRepository {
    private final EntityManager em;

    /**
     * 새 웹툰 랭킹 집합 생성
     */
    public WebtoonRankingSet save(WebtoonType webtoonType) {
        WebtoonRankingSet webtoonRankingSet = WebtoonRankingSet.create(webtoonType);
        em.persist(webtoonRankingSet);
        return webtoonRankingSet;
    }

    /**
     * 가장 최근 랭킹 집합 조회
     */
    public Long findLatestOne(WebtoonType webtoonType) {
        return em.createQuery("select max(wrs.id) from WebtoonRankingSet wrs"
                        + " where wrs.webtoonType=:webtoonType", Long.class)
                .setParameter("webtoonType", webtoonType)
                .getSingleResult();
    }
}
