package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.WebtoonRanking;
import jh.naverwebtoon.dto.response.FindNewRanking;
import jh.naverwebtoon.dto.response.WebtoonRankingDto;
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
     * 최근 10회차 중 좋아요 누적 수가 가장 높은 10개의 웹툰 리스트 조회
     */
    public List<FindNewRanking> findRankingsByRecentRounds() {
        return em.createQuery("select distinct new jh.naverwebtoon.dto.response.FindNewRanking(w.id, w.name, w.webtoonThumbnail, (select count(rl) as likeCount from RoundLike rl where rl.round.id in (select roundId from (select r.id as roundId from Round r where r.webtoon=w order by r.createdAt desc limit 10) as sub)) as TotalLikeCount)"
                        + " from Webtoon w order by TotalLikeCount desc limit 10", FindNewRanking.class)
                .getResultList();
    }

    /**
     * 가장 최근에 등록한 랭킹 조회
     */
    public List<WebtoonRanking> findLatestOne(int offset, int limit) {
        return em.createQuery("select distinct wr from WebtoonRanking wr"
                + " join fetch wr.webtoon w"
                + " join fetch w.webtoonThumbnail wt"
                + " order by wr.createdAt desc, wr.ranking asc", WebtoonRanking.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }
}
