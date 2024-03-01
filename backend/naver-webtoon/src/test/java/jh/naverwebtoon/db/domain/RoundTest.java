package jh.naverwebtoon.db.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RoundTest {

    @PersistenceContext
    EntityManager em;

    @Test
    void 복합키() {
        Webtoon webtoon = new Webtoon();
        webtoon.setName("세레나");

        em.persist(webtoon);
        em.flush();

        assertEquals("세레나", webtoon.getName());
        assertEquals(1, webtoon.getId());

        Webtoon webtoon1 = new Webtoon();
        webtoon1.setName("신의 탑");

        em.persist(webtoon1);
        em.flush();

        assertEquals("신의 탑", webtoon1.getName());
        assertEquals(2, webtoon1.getId());

        Round round = new Round();
        round.setWebtoon(webtoon);
        em.persist(round);
        em.flush();

        assertEquals(1, round.getWebtoon().getId());
        assertEquals(1, round.getRoundNumber());

        Round round1 = new Round();
        round1.setWebtoon(webtoon);
        em.persist(round1);
        em.flush();

        assertEquals(1, round1.getWebtoon().getId());
        assertEquals(2, round1.getRoundNumber());
    }
}