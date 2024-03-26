package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
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
        initRound(Long.valueOf(2), "세레나_1화_썸네일.png", "세레나_1화_원고.png", "세레니티의 소녀", ".");
        initRound(Long.valueOf(2), "세레나_2화_썸네일.png", "세레나_2화_원고.png", "두 주인",".");
        initRound(Long.valueOf(2), "세레나_3화_썸네일.png", "세레나_3화_원고.png", "이안사",".");
        initRound(Long.valueOf(2), "세레나_4화_썸네일.png", "세레나_4화_원고.png", "늙은 경영자와 어린 소녀",".");
        initRound(Long.valueOf(2), "세레나_5화_썸네일.png", "세레나_5화_원고.png", "유리 화원(1)",".");
        initRound(Long.valueOf(2), "세레나_6화_썸네일.png", "세레나_6화_원고.png", "유리 화원(2)",".");
        initRound(Long.valueOf(2), "세레나_7화_썸네일.png", "세레나_7화_원고.png", "유리 화원(3)",".");
        initRound(Long.valueOf(2), "세레나_8화_썸네일.png", "세레나_8화_원고.png", "나쁜 놈","이제부터 본 이야기 시작!");
        initRound(Long.valueOf(2), "세레나_9화_썸네일.png", "세레나_9화_원고.png", "이거 완전 공주님이네", "작중 '마리안느 드생'의 그림은 5화에서 살짝 등장했었답니다:)");
    }

    public void initRound(Long webtoonId, String thumbnail, String manuscript, String title, String authorNote) {
        Webtoon webtoon = webtoonRepository.findOne(webtoonId);
        RoundThumbnail roundThumbnail = RoundThumbnail.create(new UploadImage(thumbnail,thumbnail));
        MergeManuscript mergeManuscript = MergeManuscript.create(new UploadImage(manuscript, manuscript));
        CreateRoundReq roundReq = new CreateRoundReq();
        roundReq.setRoundTitle(title);
        roundReq.setAuthorNote(authorNote);

        Round round = Round.create(roundReq, webtoon, roundThumbnail, new ArrayList<>(), mergeManuscript);
        em.persist(round);
    }

    public Round findOne(Long roundId) {
        return em.find(Round.class, roundId);
    }

    public List<Round> findAllByWebtoonWithPaging(Long webtoonId, int offset, int limit, boolean isDesc) {
        String sql = "select r from Round r"
                + " join fetch r.roundThumbnail th"
                + " where r.webtoon.id = :webtoonId";
        if (isDesc) {
            sql += " order by r.roundNumber desc";
        } else {
            sql += " order by r.roundNumber asc";
        }

        return em.createQuery(sql, Round.class)
                .setParameter("webtoonId", webtoonId)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Round> findAllByWebtoon(Long webtoonId) {
        return em.createQuery("select r from Round r"
                + " join fetch r.roundThumbnail th"
                + " where r.webtoon.id = :webtoonId")
                .setParameter("webtoonId",webtoonId)
                .getResultList();
    }

    /**
     * 웹툰에 해당하는 전체 회차 갯수 조회
     */
    public Long findTotalCountByWebtoon(Long webtoonId) {
        return em.createQuery("select count(r) from Round r where r.webtoon.id = :webtoonId", Long.class)
                .setParameter("webtoonId", webtoonId)
                .getSingleResult();
    }

    public Long findMaxRoundNumberByWebtoonId(Long webtoonId) {
        return em.createQuery("select max(r.roundNumber) from Round r where r.webtoon.id = :webtoonId", Long.class)
                .setParameter("webtoonId", webtoonId)
                .getSingleResult();
    }

    public Round findOneDetail(Long id) {
        return em.createQuery("select r from Round r"
                + " join fetch r.webtoon w"
                + " join fetch w.member m"
                + " join fetch m.profileImage"
                + " join fetch r.mergeManuscript"
                + " where r.id=:id", Round.class)
                .setParameter("id", id)
                .getSingleResult();
    }

}
