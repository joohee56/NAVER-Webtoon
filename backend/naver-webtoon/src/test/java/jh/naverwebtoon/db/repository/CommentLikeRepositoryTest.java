package jh.naverwebtoon.db.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentLikeRepositoryTest {
    @Autowired
    private CommentLikeRepository repository;

    @Test
    void test() {
        System.out.println(repository.findTotalCountByCommentId(Long.valueOf(2)));
    }

    @Test
    void test2() {
        System.out.println(repository.findOneByCommentAndMember(Long.valueOf(1), Long.valueOf(2)));
    }

}