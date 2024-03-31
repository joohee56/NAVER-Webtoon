package jh.naverwebtoon.web.controller;

import java.util.List;
import jh.naverwebtoon.dto.request.CreateCommentReq;
import jh.naverwebtoon.dto.response.FindComments;
import jh.naverwebtoon.service.CommentService;
import jh.naverwebtoon.web.Auth;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @Auth
    @PostMapping()
    public Long createComment(@Login Long memberId, @RequestBody CreateCommentReq createCommentReq) {
        return commentService.save(memberId, createCommentReq);
    }

    @GetMapping("/{roundId}/{offset}/{limit}")
    public List<FindComments> findCommentsWithPaging(@Login Long memberId, @PathVariable("roundId") Long roundId, @PathVariable("offset") int offset, @PathVariable("limit") int limit) {
        return commentService.findAllWithPaging(memberId, roundId, offset, limit);
    }

}
