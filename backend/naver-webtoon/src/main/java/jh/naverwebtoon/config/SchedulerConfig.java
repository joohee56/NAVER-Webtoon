package jh.naverwebtoon.config;

import jh.naverwebtoon.web.controller.SocketController;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SchedulerConfig {
    private final SocketController socketController;

    @Scheduled(fixedDelay = 1000*20)  //20초마다 실행
    public void run() {
//        socketController.sendChallengeRanking();
//        socketController.sendOfficialRanking();
    }
}
