package jh.naverwebtoon.db.repository;

import java.util.List;
import jh.naverwebtoon.db.domain.comment.Comment;
import jh.naverwebtoon.dto.response.FindComment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void test() {
        List<FindComment> comments = commentRepository.findAllByRoundIdWithPaging(null, Long.valueOf(9), 0, 5);
        for (FindComment comment : comments) {
            System.out.println(comment.toString());
        }
    }

    @Test
    void start_limit_변경_테스트() {
        List<FindComment> comments = commentRepository.findAllByRoundIdWithPaging(null, Long.valueOf(9),
                6, 1);
        for (FindComment comment : comments) {
            System.out.println(comment.toString());
        }
    }

    @Test
    void 회원과_함께_댓글_조회() {
        Comment comment = commentRepository.findWithMember(Long.valueOf(1));
        System.out.println(comment.getMember().getId());
    }
}