package jh.naverwebtoon.db.repository;

import java.util.List;
import jh.naverwebtoon.dto.response.FindCommentsWithLoginRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void test() {
        List<FindCommentsWithLoginRes> comments = commentRepository.findAllByRoundIdWithPagingAndLogin(Long.valueOf(1), Long.valueOf(9), 0, 5);
        for (FindCommentsWithLoginRes comment : comments) {
            System.out.println(comment.toString());
        }
    }
}