package jh.naverwebtoon.service;

import jh.naverwebtoon.db.domain.commentReaction.CommentLike;
import jh.naverwebtoon.db.repository.CommentDislikeRepository;
import jh.naverwebtoon.db.repository.CommentLikeRepository;
import jh.naverwebtoon.db.repository.CommentRepository;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.dto.response.FindCommentLikeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final CommentDislikeRepository commentDislikeRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public FindCommentLikeRes save(Long memberId, Long commentId) {
        if (!commentDislikeRepository.findOne(memberId, commentId).isEmpty()) {
            throw new IllegalStateException("'싫어요'한 댓글입니다. 취소 후 수정해주세요.");
        }

        if (!commentLikeRepository.findOne(memberId, commentId).isEmpty()) {
            //좋아요 취소
            int res = commentLikeRepository.delete(memberId, commentId);
        } else {
            //좋아요 추가
            commentLikeRepository.save(CommentLike.create(memberRepository.findOne(memberId), commentRepository.findOne(commentId)));
        }

        Long totalCnt = commentLikeRepository.findTotalCountByCommentId(commentId);
        Long isUserLike = commentLikeRepository.findOneByCommentAndMember(memberId, commentId);
        return new FindCommentLikeRes(totalCnt, isUserLike);
    }
}
