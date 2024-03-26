package jh.naverwebtoon.web.controller;

import jh.naverwebtoon.dto.request.CreateCommentReq;
import jh.naverwebtoon.service.CommentService;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping()
    public Long createComment(@Login Long memberId, @RequestBody CreateCommentReq createCommentReq) {
        return commentService.save(memberId, createCommentReq);
    }
}
