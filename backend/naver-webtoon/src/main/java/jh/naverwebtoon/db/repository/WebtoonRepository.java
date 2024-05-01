package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.response.FindWebtoonsByMemberRes;
import jh.naverwebtoon.dto.response.SearchWebtoonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class WebtoonRepository {
    @PersistenceContext
    private EntityManager em;

    public Long save(Webtoon webtoon) {
        em.persist(webtoon);
        return webtoon.getId();
    }

    public Webtoon findOne(Long id) {
        return em.createQuery("select w from Webtoon w"
                + " join fetch w.member m"
                + " where w.id = :webtoonId", Webtoon.class)
                .setParameter("webtoonId", id)
                .getSingleResult();
    }

    public Webtoon findOneWithThumbnail(Long webtoonId) {
        return em.createQuery("select w from Webtoon w"
                + " join fetch w.webtoonThumbnail wt"
                + " where w.id = :webtoonId", Webtoon.class)
                .setParameter("webtoonId", webtoonId)
                .getSingleResult();
    }

    /**
     * 회차 리스트 상단의 웹툰 정보 조회
     */
    public Webtoon findOneByIdWithMemberAndThumbnail(Long webtoonId) {
        return em.createQuery("select w from Webtoon w"
                        + " join fetch w.member m "
                        + " join fetch m.profileImage"
                        + " join fetch w.webtoonThumbnail wt"
                        + " join fetch w.genres g"
                        + " where w.id = :webtoonId", Webtoon.class)
                .setParameter("webtoonId", webtoonId )
                .getSingleResult();
    }

    /**
     * 작품관리 페이지에서 웹툰 조회
     */
    public List<FindWebtoonsByMemberRes> findAllByMemberWithThumbnail(Long memberId) {
        return em.createQuery("select new FindWebtoonsByMemberRes(w.id, w.name, w.webtoonThumbnail.posterImage.storeFileName, w.webtoonType, (select count(c) from Comment c where c.round.webtoon=w), (select max(r.createdAt) from Round r where r.webtoon.id = w.id)) from Webtoon w"
                        + " where w.member.id = :memberId", FindWebtoonsByMemberRes.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    /**
     * 회차 등록 시 전체 웹툰 조회
     */
    public List<Object[]> findAllByMemberWithMaxRoundNumber(Long memberId) {
        return em.createQuery(
                        "select distinct w.id, w.name, (select max(r.roundNumber) from Round r where r.webtoon = w)"
                                + " from Webtoon w"
                                + " cross join Round r"
                                + " where w.member.id = :memberId")
                .setParameter("memberId", memberId)
                .getResultList();
    }

    /**
     * 웹툰 이름으로 검색
     */
    public List<SearchWebtoonDto> findAllByKeyword(String keyword, WebtoonType webtoonType, int offset, int limit) {
        return em.createQuery("select distinct new jh.naverwebtoon.dto.response.SearchWebtoonDto(w"
                        + ", (select max(r.roundNumber) from Round r where r.webtoon=w)"
                        + ", (select max(r.updatedAt) from Round r where r.webtoon=w)) from Webtoon w"
                        + " join fetch w.member m"
                        + " join fetch w.webtoonThumbnail wt"
                        + " where w.webtoonType=:webtoonType"
                        + " and function('replace',w.name, ' ', '') like concat('%', :keyword, '%')"
                        + " or function('replace', w.member.name, ' ', '') like concat('%', :keyword, '%')", SearchWebtoonDto.class)
                .setFirstResult(offset)
                .setMaxResults(limit)
                .setParameter("keyword", keyword)
                .setParameter("webtoonType", webtoonType)
                .getResultList();
    }

    /**
     * 검색 결과 총 갯수 조회
     */
    public Long findSearchCount(String keyword, WebtoonType webtoonType) {
        return em.createQuery("select distinct count(w) from Webtoon w"
                        + " where w.webtoonType=:webtoonType"
                        + " and function('replace',w.name, ' ', '') like concat('%', :keyword, '%')"
                        + " or function('replace', w.member.name, ' ', '') like concat('%', :keyword, '%')", Long.class)
                .setParameter("keyword", keyword)
                .setParameter("webtoonType", webtoonType)
                .getSingleResult();
    }


}
