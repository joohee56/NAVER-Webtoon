package jh.naverwebtoon.service;

import java.util.List;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.db.domain.Comment;
import jh.naverwebtoon.db.repository.CommentDislikeRepository;
import jh.naverwebtoon.db.repository.CommentLikeRepository;
import jh.naverwebtoon.db.repository.CommentRepository;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.RoundRepository;
import jh.naverwebtoon.dto.request.CreateCommentReq;
import jh.naverwebtoon.dto.request.CreateReplyReq;
import jh.naverwebtoon.dto.response.CommentDto;
import jh.naverwebtoon.dto.response.FindCommentRes;
import jh.naverwebtoon.dto.response.FindReply;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final RoundRepository roundRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentDislikeRepository commentDislikeRepository;

    /**
     * 댓글 저장
     */
    @Transactional
    public Long save(Long memberId, CreateCommentReq createCommentReq) {
        Member member = memberRepository.findOne(memberId);
        Round round = roundRepository.findOne(createCommentReq.getRoundId());
        Comment comment = Comment.createOrdinary(member, round, createCommentReq.getContent());
        return commentRepository.save(comment);
    }

    /**
     * 답글 저장
     */
    @Transactional
    public Long save(Long memberId, CreateReplyReq createReplyReq) {
        Member member = memberRepository.findOne(memberId);
        Round round = roundRepository.findOne(createReplyReq.getRoundId());
        Comment parentComment = commentRepository.findOne(createReplyReq.getCommentId());
        Comment comment = Comment.createReply(member, round, createReplyReq.getReplyContent(), parentComment);
        return commentRepository.save(comment);
    }

    /**
     * 댓글 삭제
     */
    @Transactional
    public int delete(Long memberId, Long commentId) {
        //댓글 작성자 본인 확인
        Comment comment = commentRepository.findWithMember(commentId);
        if (!comment.getMember().getId().equals(memberId)) {
            throw new IllegalStateException("잘못된 접근입니다.");
        }

        //연관된 엔티티 우선 삭제
        commentLikeRepository.delete(commentId);
        commentDislikeRepository.delete(commentId);

        return commentRepository.delete(commentId);
    }

    /**
     * 댓글 조회
     */
    public FindCommentRes findAllWithPaging(Long memberId, Long roundId, int offset, int limit) {
        List<CommentDto> comments = commentRepository.findAllByRoundIdWithPaging(memberId, roundId, offset, limit);
        Long totalCommentCount = commentRepository.findTotalCommentCount(roundId);
        return new FindCommentRes(totalCommentCount, comments);
    }

    /**
     * 답글 조회
     */
    public List<FindReply> findReplyAll(Long memberId, Long commentId) {
        return commentRepository.findReplyAll(memberId, commentId);
    }
}
