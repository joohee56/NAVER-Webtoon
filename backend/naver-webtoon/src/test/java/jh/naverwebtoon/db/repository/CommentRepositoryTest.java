package jh.naverwebtoon.db.repository;

import java.util.List;
import jh.naverwebtoon.dto.response.FindComments;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void test() {
        List<FindComments> comments = commentRepository.findAllByRoundIdWithPaging(null, Long.valueOf(9), 0, 5);
        for (FindComments comment : comments) {
            System.out.println(comment.toString());
        }
    }
}