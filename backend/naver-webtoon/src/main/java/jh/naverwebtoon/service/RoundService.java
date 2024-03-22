package jh.naverwebtoon.service;

import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.RoundRepository;
import jh.naverwebtoon.db.repository.WebtoonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;
    private final WebtoonRepository webtoonRepository;
    private final MemberRepository memberRepository;

//    public List<FindCreateRoundInfoRes> findCreateRoundInfo(Long memberId) {
//        Member member = memberRepository.findOne(memberId);
//        return webtoonRepository.findAllByMemberWithRoundNumber(member);
//    }
}
