package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import jh.naverwebtoon.db.domain.WebtoonRanking;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.response.FindNewRanking;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WebtoonRankingRepository {
    private final EntityManager em;

    public Long save(WebtoonRanking webtoonRanking) {
        em.persist(webtoonRanking);
        return webtoonRanking.getId();
    }

    /**
     * 가장 최근에 등록한 랭킹 조회
     */
    public List<WebtoonRanking> findLatestRankings(int offset, int limit, Long rankingSetId, WebtoonType webtoonType) {
        return em.createQuery("select distinct wr from WebtoonRanking wr"
                        + " join fetch wr.webtoon w"
                        + " join fetch w.webtoonThumbnail wt"
                        + " where wr.webtoon.webtoonType=:webtoonType"
                        + " and wr.webtoonRankingSet.id=:rankingSetId"
                        + " order by wr.rankingNo asc ", WebtoonRanking.class)
                .setParameter("webtoonType", webtoonType)
                .setParameter("rankingSetId", rankingSetId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    /**
     * 웹툰들의 최근 10회차 중 좋아요 누적 수가 가장 높은 14개의 웹툰 리스트 조회
     */
    public List<FindNewRanking> findRankingsByRecentRounds(int offset, int limit, WebtoonType webtoonType) {
        return em.createQuery("select distinct new jh.naverwebtoon.dto.response.FindNewRanking(w.id"
                        + ", (select count(rl) as likeCount from RoundLike rl where rl.round.id in (select roundId from (select r.id as roundId from Round r where r.webtoon=w order by r.createdAt desc limit 14) as sub)) as TotalLikeCount"
                        + ") from Webtoon w"
                        + " where w.webtoonType=:webtoonType"
                        + " order by TotalLikeCount desc", FindNewRanking.class)
                .setParameter("webtoonType", webtoonType)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
