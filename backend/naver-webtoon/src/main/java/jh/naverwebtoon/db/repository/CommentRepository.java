package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jh.naverwebtoon.db.domain.Comment;
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

}