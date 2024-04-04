package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.WebtoonRanking;
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
     * 최근 10회차 중 좋아요 누적 수가 가장 높은 5개의 웹툰 리스트 조회
     */
    public List findRankingsByRecentRounds() {
        return em.createQuery("select w.id, (select count(rl) as likeCount from RoundLike rl where rl.round.id in (select roundId from (select r.id as roundId from Round r where r.webtoon=w order by r.createdAt desc limit 10) as sub)) as TotalLikeCount"
                        + " from Webtoon w order by TotalLikeCount desc limit 5")
                .getResultList();
    }

    /**
     * 가장 최근에 등록한 랭킹 조회
     */
    public List<WebtoonRanking> findLatestOne() {
        return em.createQuery("select wr from WebtoonRanking wr"
                + " order by wr.createdAt desc"
                + " limit 1", WebtoonRanking.class)
                .getResultList();
    }
}
