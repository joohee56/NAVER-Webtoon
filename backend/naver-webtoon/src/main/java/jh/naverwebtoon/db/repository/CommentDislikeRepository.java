package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jh.naverwebtoon.db.domain.comment.CommentDislike;
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

    public int delete(Long commentId) {
        return em.createQuery("delete from CommentDislike cd"
                        + " where cd.comment.id = :commentId")
                .setParameter("commentId", commentId)
                .executeUpdate();
    }

    public Long findTotalCountByCommentId(Long commentId) {
        return em.createQuery("select count(cd) from CommentDislike cd"
                        + " where cd.comment.id = :commentId", Long.class)
                .setParameter("commentId", commentId)
                .getSingleResult();
    }

    /**
     * 로그인한 유저가 해당 댓글에 누른 싫어요 갯수 조회
     */
    public Long findOneByCommentAndMember(Long memberId, Long commentId) {
        return em.createQuery("select count(cd) from CommentDislike cd"
                        + " where cd.comment.id = :commentId "
                        + " and cd.member.id =:memberId", Long.class)
                .setParameter("commentId", commentId)
                .setParameter("memberId", memberId)
                .getSingleResult();
    }
}
