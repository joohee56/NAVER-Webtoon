package jh.naverwebtoon.web.controller;

import jh.naverwebtoon.dto.response.FindCommentDislikeRes;
import jh.naverwebtoon.service.CommentDislikeService;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comment/dislike")
@RequiredArgsConstructor
public class CommentDislikeController {
    private final CommentDislikeService commentDislikeService;

    @PostMapping("/{commentId}")
    public FindCommentDislikeRes createCommentDislike(@Login Long memberId, @PathVariable("commentId") Long commentId) {
        return commentDislikeService.save(memberId, commentId);
    }
}
