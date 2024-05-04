package jh.naverwebtoon.web.controller;

import java.util.List;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.dto.response.FindWebtoonRankingsRes;
import jh.naverwebtoon.dto.response.WebtoonRankingDto;
import jh.naverwebtoon.service.WebtoonRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SocketController {
    private final SimpMessageSendingOperations sendingOperations;
    private final WebtoonRankingService webtoonRankingService;

    public void sendOfficialRanking() {
        int offset =0, limit = 14;
        WebtoonType webtoonType = WebtoonType.OFFICIAL;
        List<WebtoonRankingDto> webtoonRankings = webtoonRankingService.updateRanking(offset, limit, webtoonType);
        sendingOperations.convertAndSend("/send/official", FindWebtoonRankingsRes.create(webtoonRankings));
    }

    public void sendChallengeRanking() {
        int offset =0, limit = 14;
        WebtoonType webtoonType = WebtoonType.CHALLENGE;
        List<WebtoonRankingDto> webtoonRankings = webtoonRankingService.updateRanking(offset, limit, webtoonType);
        sendingOperations.convertAndSend("/send/challenge", FindWebtoonRankingsRes.create(webtoonRankings));
    }
}
