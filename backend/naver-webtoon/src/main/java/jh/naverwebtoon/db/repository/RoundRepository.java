package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jh.naverwebtoon.db.domain.Round;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoundRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Round round) {
        em.persist(round);
        return round.getId();
    }

    public Round findOne(Long roundId) {
        return em.find(Round.class, roundId);
    }

    public List<Round> findAllByWebtoonWithPaging(Long webtoonId, int offset, int limit, boolean isDesc) {
        String sql = "select r from Round r"
                + " join fetch r.roundThumbnail th"
                + " where r.webtoon.id = :webtoonId";
        if (isDesc) {
            sql += " order by r.roundNumber desc";
        } else {
            sql += " order by r.roundNumber asc";
        }

        return em.createQuery(sql, Round.class)
                .setParameter("webtoonId", webtoonId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Round> findAllByWebtoon(Long webtoonId) {
        return em.createQuery("select r from Round r"
                + " join fetch r.roundThumbnail th"
                + " where r.webtoon.id = :webtoonId")
                .setParameter("webtoonId",webtoonId)
                .getResultList();
    }

    /**
     * 웹툰에 해당하는 전체 회차 갯수 조회
     */
    public Long findTotalCountByWebtoon(Long webtoonId) {
        return em.createQuery("select count(r) from Round r where r.webtoon.id = :webtoonId", Long.class)
                .setParameter("webtoonId", webtoonId)
                .getSingleResult();
    }

    /**
     * 웹툰의 다음 회차 조회
     */
    public Long findMaxRoundNumberByWebtoonId(Long webtoonId) {
        return em.createQuery("select max(r.roundNumber) from Round r where r.webtoon.id = :webtoonId", Long.class)
                .setParameter("webtoonId", webtoonId)
                .getSingleResult();
    }

    /**
     * 웹툰 디테일 정보 조회
     */
    public Round findOneDetail(Long id) {
        return em.createQuery("select r from Round r"
                + " join fetch r.webtoon w"
                + " join fetch w.member m"
                + " join fetch m.profileImage"
                + " join fetch r.mergeManuscript"
                + " where r.id=:id", Round.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    /**
     * 웹툰에 해당하는 전체 회차 리스트 조회 + 댓글 갯수
     */
    public void findAllByWebtoonWithCommentCount(Long webtoonId) {

    }

}
