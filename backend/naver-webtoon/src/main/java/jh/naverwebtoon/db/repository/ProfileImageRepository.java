package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileImageRepository {
    @PersistenceContext
    private EntityManager em;

}
