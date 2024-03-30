package jh.naverwebtoon.service;

import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.db.domain.RoundLike;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.RoundLikeRepository;
import jh.naverwebtoon.db.repository.RoundRepository;
import jh.naverwebtoon.dto.response.CreateRoundLikeRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoundLikeService {
    private final RoundLikeRepository roundLikeRepository;
    private final MemberRepository memberRepository;
    private final RoundRepository roundRepository;

    @Transactional
    public CreateRoundLikeRes save(Long memberId, Long roundId) {
        if (roundLikeRepository.findCountByMemberAndRound(memberId, roundId) == 1) {
            //좋아요 취소
            roundLikeRepository.delete(memberId, roundId);
        } else {
            Member member = memberRepository.findOne(memberId);
            Round round = roundRepository.findOne(roundId);
            roundLikeRepository.save(RoundLike.create(member, round));
        }
        Long totalCnt = roundLikeRepository.findTotalCountByRoundId(roundId);
        Long isUserLike = roundLikeRepository.findCountByMemberAndRound(memberId, roundId);
        return new CreateRoundLikeRes(totalCnt, isUserLike);
    }
}
