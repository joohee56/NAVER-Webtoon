package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.commentReaction.CommentDislike;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentDislikeRepository {
    private final EntityManager em;

    public void save(CommentDislike commentDislike) {
        em.persist(commentDislike);
    }

    public List<CommentDislike> findOne(Long memberId, Long commentId) {
        return em.createQuery("select cd from CommentDislike cd"
                        + " where cd.member.id = :memberId"
                        + " and cd.comment.id = :commentId", CommentDislike.class)
                .setParameter("memberId", memberId)
                .setParameter("commentId", commentId)
                .getResultList();
    }

    public int delete(Long memberId, Long commentId) {
        return em.createQuery("delete from CommentDislike cd"
                        + " where cd.member.id = :memberId"
                        + " and cd.comment.id = :commentId")
                .setParameter("memberId", memberId)
                .setParameter("commentId", commentId)
                .executeUpdate();
    }

    public Long findTotalCountByCommentId(Long commentId) {
        return em.createQuery("select count(cl) from CommentDislike cd"
                        + " where cd.comment.id = :commentId", Long.class)
                .setParameter("commentId", commentId)
                .getSingleResult();
    }
}
