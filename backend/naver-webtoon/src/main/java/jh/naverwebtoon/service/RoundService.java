package jh.naverwebtoon.service;

import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.db.repository.RoundRepository;
import jh.naverwebtoon.dto.response.FindRoundDetailRes;
import jh.naverwebtoon.dto.response.FindRoundsByWebtoon;
import jh.naverwebtoon.dto.response.FindRoundsByWebtoonWithPaging;
import jh.naverwebtoon.dto.response.RoundListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;

    /**
     * 웹툰에 해당하는 회차 리스트 조회 (페이징)
     */
    public FindRoundsByWebtoonWithPaging findRoundsByWebtoonWithPaing(Long webtoonId, int offset, int limit, boolean isDesc) {
        List<Round> rounds = roundRepository.findAllByWebtoonWithPaging(webtoonId, offset, limit, isDesc);
        Long totalRoundCount = roundRepository.findTotalCountByWebtoon(webtoonId);  //총 회차 갯수

        List<RoundListDto> roundDtos = rounds.stream().map(round -> RoundListDto.create(round))
                .collect(Collectors.toList());
        int pageCount = (int) Math.ceil((double)totalRoundCount / limit);  //총 페이지 갯수

        return new FindRoundsByWebtoonWithPaging(roundDtos,pageCount, totalRoundCount);
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
    public FindRoundDetailRes findOneDetail(Long roundId) {
        return FindRoundDetailRes.create(roundRepository.findOneDetail(roundId));
    }
}
