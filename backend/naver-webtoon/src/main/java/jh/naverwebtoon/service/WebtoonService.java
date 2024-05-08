package jh.naverwebtoon.service;

import java.util.ArrayList;
import java.util.List;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.UploadImage;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.WebtoonRepository;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
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
    private final FileStore fileStore;

    @Transactional
    public Webtoon createWebtoon(Long memberId, CreateWebtoonReq createWebtoonReq) {
        Member member = memberRepository.findOne(memberId);
        UploadImage posterImage = fileStore.storeFile(createWebtoonReq.getPosterImage());
        UploadImage horizontalImage = fileStore.storeFile(createWebtoonReq.getHorizontalImage());
        WebtoonThumbnail webtoonThumbnail = WebtoonThumbnail.create(posterImage, horizontalImage);
        Webtoon webtoon = Webtoon.create(member, createWebtoonReq, webtoonThumbnail);
        Long webtoonId = webtoonRepository.save(webtoon);
        return webtoonRepository.findOneWithMember(webtoonId);
    }

    public List<FindWebtoonsByMemberRes> findAllByMember(Long memberId) {
        return webtoonRepository.findAllByMemberWithThumbnail(memberId);
    }

    public List<FindCreateRoundInfoRes> findCreateRoundInfo(Long memberId) {
        List<FindCreateRoundInfoRes> res = new ArrayList<>();
        List<Object[]> webtoons = webtoonRepository.findAllByMemberWithMaxRoundNumber(memberId);
        for (Object[] webtoon : webtoons) {
            res.add(FindCreateRoundInfoRes.create((Long) webtoon[0], (String) webtoon[1], webtoon[2]));
        }
        return res;
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
