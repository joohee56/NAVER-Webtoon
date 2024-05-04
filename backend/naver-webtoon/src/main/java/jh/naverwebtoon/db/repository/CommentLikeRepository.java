package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.comment.CommentLike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentLikeRepository {
    private final EntityManager em;

    public void save(CommentLike commentLike) {
        em.persist(commentLike);
    }

    public List<CommentLike> findOne(Long memberId, Long commentId) {
        return em.createQuery("select cl from CommentLike cl"
                + " where cl.member.id = :memberId"
                + " and cl.comment.id = :commentId", CommentLike.class)
                .setParameter("memberId", memberId)
                .setParameter("commentId", commentId)
                .getResultList();
    }

    public int delete(Long memberId, Long commentId) {
        return em.createQuery("delete from CommentLike cl"
                + " where cl.member.id = :memberId"
                + " and cl.comment.id = :commentId")
                .setParameter("memberId", memberId)
                .setParameter("commentId", commentId)
                .executeUpdate();
    }

    public int delete(Long commentId) {
        return em.createQuery("delete from CommentLike cl"
                        + " where cl.comment.id = :commentId")
                .setParameter("commentId", commentId)
                .executeUpdate();
    }

    public Long findTotalCountByCommentId(Long commentId) {
        return em.createQuery("select count(cl) from CommentLike cl"
                + " where cl.comment.id = :commentId", Long.class)
                .setParameter("commentId", commentId)
                .getSingleResult();
    }

    /**
     * 로그인한 유저가 해당 댓글에 누른 좋아요 갯수 조회
     */
    public Long findOneByCommentAndMember(Long memberId, Long commentId) {
        return em.createQuery("select count(cl) from CommentLike cl"
                + " where cl.comment.id = :commentId "
                + " and cl.member.id =:memberId", Long.class)
                .setParameter("commentId", commentId)
                .setParameter("memberId", memberId)
                .getSingleResult();
    }
}
