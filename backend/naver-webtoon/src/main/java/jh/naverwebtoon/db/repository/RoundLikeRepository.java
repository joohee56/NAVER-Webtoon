package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jh.naverwebtoon.db.domain.RoundLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoundLikeRepository {
    private final EntityManager em;

    public void save(RoundLike roundLike) {
        em.persist(roundLike);
    }

    public Long findTotalCountByRoundId(Long roundId) {
        return em.createQuery("select count(rl) from RoundLike  rl"
                + " where rl.round.id=:roundId", Long.class)
                .setParameter("roundId", roundId)
                .getSingleResult();
    }

    public Long findCountByMemberAndRound(Long memberId, Long roundId) {
        return em.createQuery("select count(rl) from RoundLike rl"
                + " where rl.round.id=:roundId"
                + " and rl.member.id=:memberId", Long.class)
                .setParameter("roundId", roundId)
                .setParameter("memberId", memberId)
                .getSingleResult();
    }

    public int delete(Long memberId, Long roundId) {
        return em.createQuery("delete from RoundLike rl"
                + " where rl.round.id=:roundId"
                + " and rl.member.id=:memberId")
                .setParameter("roundId", roundId)
                .setParameter("memberId", memberId)
                .executeUpdate();

    }
}
