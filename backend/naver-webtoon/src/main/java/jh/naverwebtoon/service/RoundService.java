package jh.naverwebtoon.service;

import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.db.repository.RoundRepository;
import jh.naverwebtoon.dto.response.FindRoundsByWebtoon;
import jh.naverwebtoon.dto.response.RoundListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoundService {
    private final RoundRepository roundRepository;

    public FindRoundsByWebtoon findRoundsByWebtoonWithPaing(Long webtoonId, int offset, int limit, boolean isDesc) {
        List<Round> rounds = roundRepository.findAllByWebtoonWithPaging(webtoonId, offset, limit, isDesc);
        Long totalRoundCount = roundRepository.findTotalCountByWebtoon(webtoonId);

        List<RoundListDto> roundDtos = rounds.stream().map(round -> RoundListDto.create(round))
                .collect(Collectors.toList());
        int pageCount = (int) Math.ceil((double)totalRoundCount / limit);

        return new FindRoundsByWebtoon(roundDtos,pageCount, totalRoundCount);
    }

}
