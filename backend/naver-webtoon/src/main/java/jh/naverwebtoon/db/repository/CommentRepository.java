package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jh.naverwebtoon.db.domain.Comment;
import jh.naverwebtoon.dto.response.FindCommentsWithLoginRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Comment comment) {
        em.persist(comment);
        return comment.getId();
    }

    public Comment findOne(Long commentId) {
        return em.find(Comment.class, commentId);
    }

    public List<FindCommentsWithLoginRes> findAllByRoundIdWithPagingAndLogin(Long memberId, Long roundId, int offset, int limit) {
        return em.createQuery("select new jh.naverwebtoon.dto.response.FindCommentsWithLoginRes(c.id, c.content, c.member.loginId, c.member.name, c.updatedAt,"
                        + " (select count(cl) from CommentLike cl where cl.comment = c) as likeTotalCnt,"
                        + " (select count(cl) from CommentLike cl where cl.comment = c and cl.member.id =:memberId),"
                        + " (select count(cd) from CommentDislike cd where cd.comment = c),"
                        + " (select count(cd) from CommentDislike cd where cd.comment = c and cd.member.id =:memberId))"
                        + " from Comment c"
                        + " where c.round.id=:roundId"
                        + " order by likeTotalCnt desc, c.updatedAt desc", FindCommentsWithLoginRes.class)
                .setParameter("roundId", roundId)
                .setParameter("memberId", memberId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

}