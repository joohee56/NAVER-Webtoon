package jh.naverwebtoon.web.controller;

import jh.naverwebtoon.dto.response.CreateRoundLikeRes;
import jh.naverwebtoon.service.RoundLikeService;
import jh.naverwebtoon.web.Auth;
import jh.naverwebtoon.web.Login;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/round/like")
@RequiredArgsConstructor
public class RoundLikeController {
    private final RoundLikeService roundLikeService;

    @Auth
    @PostMapping()
    public CreateRoundLikeRes createRoundLike(@Login Long memberId, @RequestParam("roundId") Long roundId) {
        return roundLikeService.save(memberId, roundId);
    }
}
