package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.dto.response.FindRoundsManageRes;
import jh.naverwebtoon.dto.response.RoundListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoundRepository {
    private EntityManager em;

    public Long save(Round round) {
        em.persist(round);
        return round.getId();
    }

    public Round findOne(Long roundId) {
        return em.find(Round.class, roundId);
    }

    public List<RoundListDto> findAllByWebtoonWithPaging(Long webtoonId, int offset, int limit, boolean isDesc) {
        String sql = "select new jh.naverwebtoon.dto.response.RoundListDto(r.id, r.roundThumbnail.thumbnail.storeFileName, r.roundNumber, r.roundTitle, r.createdAt,"
                + " (select count(rl) from RoundLike rl where rl.round=r))"
                + " from Round r"
                + " where r.webtoon.id = :webtoonId";
        if (isDesc) {
            sql += " order by r.roundNumber desc";
        } else {
            sql += " order by r.roundNumber asc";
        }

        return em.createQuery(sql, RoundListDto.class)
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
    public Round findOneDetail(Long roundId) {
        return em.createQuery("select r from Round r"
                + " join fetch r.webtoon w"
                + " join fetch w.member m"
                + " join fetch m.profileImage"
                + " join fetch r.mergeManuscript"
                + " where r.id=:roundId", Round.class)
                .setParameter("roundId", roundId)
                .getSingleResult();
    }

    /**
     * 웹툰 회차 관리 정보 조회 (웹툰 정보, 회차 리스트, 댓글 갯수, 좋아요 갯수)
     */
    public List<FindRoundsManageRes> findAllByWebtoonWithManage(Long webtoonId, int offset, int limit) {
        return em.createQuery("select new jh.naverwebtoon.dto.response.FindRoundsManageRes(r.id, r.roundNumber, r.roundThumbnail.thumbnail.storeFileName, r.roundTitle, r.createdAt, r.updatedAt,"
                + " (select count(rl) from RoundLike rl where rl.round = r),"
                + " (select count(c) from Comment c where c.round = r))"
                + " from Round r"
                + " where r.webtoon.id = :webtoonId"
                + " order by r.roundNumber desc", FindRoundsManageRes.class)
                .setParameter("webtoonId", webtoonId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

}
