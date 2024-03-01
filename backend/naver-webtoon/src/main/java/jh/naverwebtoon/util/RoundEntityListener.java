package jh.naverwebtoon.util;

import jakarta.persistence.PrePersist;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.db.repository.RoundRepository;
import org.springframework.stereotype.Component;

@Component
public class RoundEntityListener {

    @PrePersist
    public void prePersist(Round round) {
        if(round.getRoundNumber() == null) {
            round.setRoundNumber(generateNextRoundNumber(round.getWebtoon()));
        }
    }

    private Long generateNextRoundNumber(Webtoon webtoon) {
        RoundRepository roundRepository = BeanUtils.getBean(RoundRepository.class);
        Long maxRoundNumber = roundRepository.findMaxRoundNumberByWebtoonId(webtoon.getId());
        return maxRoundNumber != null ? maxRoundNumber + 1 : 1;
    }
}
