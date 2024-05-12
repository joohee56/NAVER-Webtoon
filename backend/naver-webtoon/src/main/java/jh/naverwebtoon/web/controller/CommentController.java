package jh.naverwebtoon.web.controller;

import jakarta.validation.Valid;
import java.util.List;
import jh.naverwebtoon.dto.request.CreateCommentReq;
import jh.naverwebtoon.dto.request.CreateReplyReq;
import jh.naverwebtoon.dto.response.CommentDto;
import jh.naverwebtoon.dto.response.FindCommentRes;
import jh.naverwebtoon.dto.response.FindReply;
import jh.naverwebtoon.service.CommentService;
import jh.naverwebtoon.web.Auth;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    /**
     * 댓글 생성
     */
    @Auth
    @PostMapping()
    public Long createComment(@Login Long memberId, @Valid @RequestBody CreateCommentReq createCommentReq) {
        return commentService.save(memberId, createCommentReq);
    }

    /**
     * 답글 생성
     */
    @Auth
    @PostMapping("/reply")
    public Long createReply(@Login Long memberId, @Valid @RequestBody CreateReplyReq createReplyReq) {
        return commentService.save(memberId, createReplyReq);
    }

    /**
     * 댓글 삭제
     */
    @Auth
    @DeleteMapping("/comment/{commentId}")
    public int deleteComment(@Login Long memberId, @PathVariable("commentId") Long commentId) {
        return commentService.delete(memberId, commentId);
    }

    /**
     * 댓글 조회
     */
    @GetMapping("/{roundId}/{offset}/{limit}")
    public FindCommentRes findCommentsWithPaging(@Login Long memberId, @PathVariable("roundId") Long roundId, @PathVariable("offset") int offset, @PathVariable("limit") int limit) {
        return commentService.findAllWithPaging(memberId, roundId, offset, limit);
    }

    /**
     * 답글 조회
     */
    @GetMapping("/reply")
    public List<FindReply> findReplys(@Login Long memberId, @RequestParam("commentId") Long commentId) {
        return commentService.findReplyAll(memberId, commentId);
    }

}
