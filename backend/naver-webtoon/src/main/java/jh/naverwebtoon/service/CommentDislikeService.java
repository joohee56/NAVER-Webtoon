package jh.naverwebtoon.service;

import jh.naverwebtoon.db.domain.commentReaction.CommentDislike;
import jh.naverwebtoon.db.repository.CommentDislikeRepository;
import jh.naverwebtoon.db.repository.CommentLikeRepository;
import jh.naverwebtoon.db.repository.CommentRepository;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.dto.response.CommentDislikeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentDislikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final CommentDislikeRepository commentDislikeRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public CommentDislikeRes save(Long memberId, Long commentId) {
        if (!commentLikeRepository.findOne(memberId, commentId).isEmpty()) {
            throw new IllegalStateException("'좋아요'한 댓글입니다. 취소 후 수정해주세요.");
        }

        if (!commentDislikeRepository.findOne(memberId, commentId).isEmpty()) {
            //싫어요 취소
            int res = commentDislikeRepository.delete(memberId, commentId);
        } else {
            //싫어요 추가
            commentDislikeRepository.save(CommentDislike.create(memberRepository.findOne(memberId), commentRepository.findOne(commentId)));
        }

        Long totalCnt = commentDislikeRepository.findTotalCountByCommentId(commentId);
        Long isUserDislike = commentDislikeRepository.findOneByCommentAndMember(memberId, commentId);
        return new CommentDislikeRes(totalCnt, isUserDislike);
    }
}
