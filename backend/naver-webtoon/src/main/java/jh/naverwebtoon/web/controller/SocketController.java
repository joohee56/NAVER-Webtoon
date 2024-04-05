package jh.naverwebtoon.web.controller;

import java.util.List;
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

    public void findRanking() {
        List<WebtoonRankingDto> webtoonRankings = webtoonRankingService.updateRanking();
        sendingOperations.convertAndSend("/send", FindWebtoonRankingsRes.create(webtoonRankings));
    }
}
