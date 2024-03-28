package jh.naverwebtoon.service;

import java.util.List;
import jh.naverwebtoon.db.domain.Comment;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.db.repository.CommentRepository;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.RoundRepository;
import jh.naverwebtoon.dto.request.CreateCommentReq;
import jh.naverwebtoon.dto.response.FindCommentsWithLoginRes;
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

    @Transactional
    public Long save(Long memberId, CreateCommentReq createCommentReq) {
        Member member = memberRepository.findOne(memberId);
        Round round = roundRepository.findOne(createCommentReq.getRoundId());
        Comment comment = Comment.create(member, round, createCommentReq.getContent());
        return commentRepository.save(comment);
    }

    public List<FindCommentsWithLoginRes> findAllWithPagingAndLogin(Long memberId, Long roundId, int offset, int limit) {
        return commentRepository.findAllByRoundIdWithPagingAndLogin(memberId, roundId, offset, limit);
    }
}
