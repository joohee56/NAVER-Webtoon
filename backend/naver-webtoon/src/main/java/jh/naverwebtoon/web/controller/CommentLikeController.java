package jh.naverwebtoon.web.controller;

import jh.naverwebtoon.dto.response.FindCommentLikeRes;
import jh.naverwebtoon.service.CommentLikeService;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment/like")
@RequiredArgsConstructor
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @PostMapping()
    public FindCommentLikeRes createCommentLike(@Login Long memberId, @RequestParam("commentId") Long commentId) {
        return commentLikeService.save(memberId, commentId);
    }
}
