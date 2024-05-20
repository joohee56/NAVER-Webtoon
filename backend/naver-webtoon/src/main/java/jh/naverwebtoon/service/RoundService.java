package jh.naverwebtoon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.MergeManuscript;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.db.domain.RoundThumbnail;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.db.repository.RoundLikeRepository;
import jh.naverwebtoon.db.repository.RoundRepository;
import jh.naverwebtoon.db.repository.WebtoonRepository;
import jh.naverwebtoon.dto.request.CreateRoundReq;
import jh.naverwebtoon.dto.response.FindRoundDetailRes;
import jh.naverwebtoon.dto.response.FindRoundManageInfoRes;
import jh.naverwebtoon.dto.response.FindRoundsByWebtoon;
import jh.naverwebtoon.dto.response.FindRoundsByWebtoonWithPaging;
import jh.naverwebtoon.dto.response.FindRoundsManageRes;
import jh.naverwebtoon.dto.response.RoundListDto;
import jh.naverwebtoon.util.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class RoundService {
    private final RoundRepository roundRepository;
    private final RoundLikeRepository roundLikeRepository;
    private final WebtoonRepository webtoonRepository;
    private final FileStore fileStore;

    /**
     * 웹툰에 해당하는 회차 리스트 조회 (페이징)
     */
    public FindRoundsByWebtoonWithPaging findRoundsByWebtoonWithPaing(Long webtoonId, int offset, int limit, boolean isDesc) {
        List<RoundListDto> rounds = roundRepository.findAllByWebtoonWithPaging(webtoonId, offset, limit, isDesc);
        Long totalRoundCount = roundRepository.findTotalCountByWebtoon(webtoonId);  //총 회차 갯수

        int pageCount = totalRoundCount==0 ? 1 : (int) Math.ceil((double)totalRoundCount / limit);  //총 페이지 갯수

        return new FindRoundsByWebtoonWithPaging(rounds,pageCount, totalRoundCount);
    }

    /**
     * 웹툰에 해당하는 전체 회차 리스트 조회
     */
    public List<FindRoundsByWebtoon> findRoundsByWebtoon(Long webtoonId) {
        return roundRepository.findAllByWebtoon(webtoonId).stream()
                .map(round -> FindRoundsByWebtoon.create(round))
                .collect(Collectors.toList());
    }

    /**
     * 회차 디테일 조회
     */
    public FindRoundDetailRes findOneDetail(Long memberId, Long roundId) {
        Long totalLikeCnt = roundLikeRepository.findTotalCountByRoundId(roundId);
        Long isUserLike = Long.valueOf(0);
        if (memberId != null) {
            isUserLike = roundLikeRepository.findCountByMemberAndRound(memberId, roundId);
        }
        return FindRoundDetailRes.create(roundRepository.findOneDetail(roundId), totalLikeCnt, isUserLike);
    }

    /**
     * 회차 저장
     */
    @Transactional
    public Long save(Long memberId, CreateRoundReq createRoundReq) {
        //웹툰에 관한 memberId 권한 체크
        Long authorMemberId = webtoonRepository.findOneWithMember(createRoundReq.getWebtoonId()).getMember().getId();
        if (!authorMemberId.equals(memberId)) {
            log.info("reqMemberId = {}, authorMemberId = {}", memberId, authorMemberId);
            throw new IllegalStateException("잘못된 접근입니다.");
        }

        Webtoon webtoon = webtoonRepository.findOneWithMember(createRoundReq.getWebtoonId());
        RoundThumbnail roundThumbnail = RoundThumbnail.create(fileStore.storeFile(createRoundReq.getThumbnail()));
        /* 파일이 너무 많이 생성되는 것을 방지하기 위해 보류
        List<Manuscript> manuscripts = createRoundReq.getManuscripts().stream()
                .map(multipartFile -> Manuscript.create(fileStore.storeFile(multipartFile)))
                .collect(Collectors.toList());
         */
        MergeManuscript mergeManuscript = MergeManuscript.create(fileStore.storeFile(createRoundReq.getMergeManuscript()));
        Round round = Round.create(createRoundReq, webtoon, roundThumbnail, new ArrayList<>(), mergeManuscript);
        return roundRepository.save(round);
    }

    /**
     * 회차 관리 페이지 조회
     */
    public FindRoundManageInfoRes findRoundManageInfo(Long webtoonId, int offset, int limit) {
        Webtoon webtoon = webtoonRepository.findOneWithThumbnail(webtoonId);
        List<FindRoundsManageRes> rounds = roundRepository.findAllByWebtoonWithManage(webtoonId, offset, limit);

        Long totalRoundCount = roundRepository.findTotalCountByWebtoon(webtoonId);  //총 회차 갯수
        int pageCount = totalRoundCount==0 ? 1 : (int) Math.ceil((double)totalRoundCount / limit);  //총 페이지 갯수

        return FindRoundManageInfoRes.create(webtoon, rounds, pageCount);
    }
}
