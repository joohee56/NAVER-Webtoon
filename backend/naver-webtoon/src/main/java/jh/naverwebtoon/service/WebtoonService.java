package jh.naverwebtoon.service;

import java.util.List;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.WebtoonGenreRepository;
import jh.naverwebtoon.db.repository.WebtoonRepository;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import jh.naverwebtoon.dto.request.EditWebtoonReq;
import jh.naverwebtoon.dto.response.FindCreateRoundInfoRes;
import jh.naverwebtoon.dto.response.FindEditWebtoonRes;
import jh.naverwebtoon.dto.response.FindWebtoonDetailRes;
import jh.naverwebtoon.dto.response.FindWebtoonsByMemberRes;
import jh.naverwebtoon.dto.response.SearchCountRes;
import jh.naverwebtoon.dto.response.SearchRes;
import jh.naverwebtoon.dto.response.SearchWebtoonDto;
import jh.naverwebtoon.util.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WebtoonService {
    private final WebtoonRepository webtoonRepository;
    private final MemberRepository memberRepository;
    private final WebtoonGenreRepository webtoonGenreRepository;
    private final FileStore fileStore;

    @Transactional
    public Webtoon createWebtoon(Long memberId, CreateWebtoonReq createWebtoonReq) {
        Member member = memberRepository.findOne(memberId);
        WebtoonThumbnail webtoonThumbnail = fileStore.createWebtoonThumbnail(createWebtoonReq.getPosterImage(), createWebtoonReq.getHorizontalImage());
        Webtoon webtoon = Webtoon.create(member, createWebtoonReq, webtoonThumbnail);
        Long webtoonId = webtoonRepository.save(webtoon);
        return webtoonRepository.findOneWithMember(webtoonId);
    }

    @Transactional
    public FindEditWebtoonRes editWebtoon(Long loginId, EditWebtoonReq editWebtoonReq) {
        //변경감지를 위해 준영속 -> 영속 상태 변경
        Webtoon findWebtoon = webtoonRepository.findOneWithMemberAndThumbnail(editWebtoonReq.getWebtoonId());
        if (findWebtoon.getMember().getId() != loginId) {
            throw new IllegalStateException("잘못된 접근입니다.");
        }
        //웹툰이 갖고 있던 기존 장르 삭제
        webtoonGenreRepository.deleteAllByWebtoonId(editWebtoonReq.getWebtoonId());
        //썸네일 교체
        fileStore.changeWebtoonThumbnail(editWebtoonReq.getPosterImage(), editWebtoonReq.getHorizontalImage(), findWebtoon);
        findWebtoon.edit(editWebtoonReq);
        return FindEditWebtoonRes.create(findWebtoon);
    }

    public List<FindWebtoonsByMemberRes> findAllByMember(Long memberId) {
        return webtoonRepository.findAllByMemberWithThumbnail(memberId);
    }

    public List<FindCreateRoundInfoRes> findCreateRoundInfo(Long memberId) {
        return webtoonRepository.findAllByMemberWithMaxRoundNumber(memberId);
    }

    public FindWebtoonDetailRes findWebtoonDetail(Long webtoonId) {
        return FindWebtoonDetailRes.create(webtoonRepository.findOneByIdWithMemberAndThumbnail(webtoonId));
    }

    public FindEditWebtoonRes findEditWebtoonInfo(Long webtoonId) {
        return FindEditWebtoonRes.create(webtoonRepository.findOneByIdWithMemberAndThumbnail(webtoonId));
    }

    public SearchRes search(String keyword, int offset, int limit) {
        keyword = keyword.replaceAll(" ", "");
        List<SearchWebtoonDto> officials = webtoonRepository.findAllByKeyword(keyword, WebtoonType.OFFICIAL, offset, limit);
        List<SearchWebtoonDto> challenges = webtoonRepository.findAllByKeyword(keyword, WebtoonType.CHALLENGE, offset, limit);
        Long totalOfficialCount = webtoonRepository.findSearchCount(keyword, WebtoonType.OFFICIAL);
        Long totalChallengeCount = webtoonRepository.findSearchCount(keyword, WebtoonType.CHALLENGE);
        return new SearchRes(officials, challenges, totalOfficialCount, totalChallengeCount);
    }

    public List<SearchWebtoonDto> searchAllByWebtoonType(String keyword, WebtoonType webtoonType, int offset, int limit) {
        keyword = keyword.replaceAll(" ", "");
        return webtoonRepository.findAllByKeyword(keyword, webtoonType, offset, limit);
    }

    public SearchCountRes searchCount(String keyword) {
        keyword = keyword.replaceAll(" ", "");
        Long totalOfficialCount = webtoonRepository.findSearchCount(keyword, WebtoonType.OFFICIAL);
        Long totalChallengeCount = webtoonRepository.findSearchCount(keyword, WebtoonType.CHALLENGE);
        return new SearchCountRes(totalOfficialCount, totalChallengeCount);
    }
}
