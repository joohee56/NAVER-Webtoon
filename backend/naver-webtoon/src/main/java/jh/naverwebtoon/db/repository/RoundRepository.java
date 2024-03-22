package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import jh.naverwebtoon.db.domain.Manuscript;
import jh.naverwebtoon.db.domain.MergeManuscript;
import jh.naverwebtoon.db.domain.Round;
import jh.naverwebtoon.db.domain.RoundThumbnail;
import jh.naverwebtoon.db.domain.UploadImage;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.request.CreateRoundReq;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class RoundRepository {

    @PersistenceContext
    private EntityManager em;
    private final WebtoonRepository webtoonRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        init1();
        init2();
    }

    public void init1() {
        Webtoon webtoon = webtoonRepository.findOne(Long.valueOf(2));
        RoundThumbnail roundThumbnail = RoundThumbnail.create(new UploadImage("세레나_회차_썸네일.png","세레나_회차_썸네일.png"));
        List<Manuscript> manuscripts = new ArrayList<>();
        manuscripts.add(Manuscript.create(new UploadImage("원고1.jpg", "원고1.jpg")));
        manuscripts.add(Manuscript.create(new UploadImage("원고2.jpg", "원고2.jpg")));
        manuscripts.add(Manuscript.create(new UploadImage("원고3.jpg", "원고3.jpg")));
        MergeManuscript mergeManuscript = MergeManuscript.create(new UploadImage("세레나_원고.png", "세레나_원고.png"));
        CreateRoundReq roundReq = new CreateRoundReq();
        roundReq.setRoundTitle("두 개의 그림(2)");
        roundReq.setAuthorNote("수정본이 재업로드되었습니다.");

        Round round = Round.create(roundReq, webtoon, roundThumbnail, manuscripts, mergeManuscript);
        em.persist(round);
    }
    public void init2() {
        Webtoon webtoon = webtoonRepository.findOne(Long.valueOf(2));
        RoundThumbnail roundThumbnail = RoundThumbnail.create(new UploadImage("세레나_회차_썸네일.png","세레나_회차_썸네일.png"));
        List<Manuscript> manuscripts = new ArrayList<>();
        manuscripts.add(Manuscript.create(new UploadImage("원고1.jpg", "원고1.jpg")));
        manuscripts.add(Manuscript.create(new UploadImage("원고2.jpg", "원고2.jpg")));
        manuscripts.add(Manuscript.create(new UploadImage("원고3.jpg", "원고3.jpg")));
        MergeManuscript mergeManuscript = MergeManuscript.create(new UploadImage("세레나_원고.png", "세레나_원고.png"));
        CreateRoundReq roundReq = new CreateRoundReq();
        roundReq.setRoundTitle("두 개의 그림(1)");
        roundReq.setAuthorNote("");

        Round round = Round.create(roundReq, webtoon, roundThumbnail, manuscripts, mergeManuscript);
        em.persist(round);
    }

    public Long findMaxRoundNumberByWebtoonId(Long webtoonId) {
        return em.createQuery("select max(r.roundNumber) from Round r where r.webtoon.id = :webtoonId", Long.class)
                .setParameter("webtoonId", webtoonId)
                .getSingleResult();
    }

}
