package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.UploadImage;
import jh.naverwebtoon.db.domain.WebtoonGenre;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class WebtoonRepository {
    @PersistenceContext
    private EntityManager em;
    private final MemberRepository memberRepository;
    private final GenreRepository genreRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        initWebtoon1();
        initWebtoon2();
    }

    public void initWebtoon1() {
        Member member = memberRepository.findOne(Long.valueOf(1));

        CreateWebtoonReq createWebtoonReq = new CreateWebtoonReq();
        createWebtoonReq.setName("청춘 블라썸");
        createWebtoonReq.setWebtoonType(WebtoonType.EPISODE);
        createWebtoonReq.setTags(new ArrayList<>());

        List<GenreEnum> genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.ROMANCE);
        genreEnums.add(GenreEnum.EMOTION);

        List<Genre> genres = new ArrayList<>();
        for (GenreEnum genreEnum : genreEnums) {
            genres.add(genreRepository.findByGenreEnum(genreEnum));
        }

        List<WebtoonGenre> webtoonGenres = new ArrayList<>();
        for (Genre genre : genres) {
            webtoonGenres.add(WebtoonGenre.create(genre));
        }

        createWebtoonReq.setOneLineSummary("푸르지 않아도 괜찮아!");
        createWebtoonReq.setSummary("이제 막 청춘을 시작하고 있는 아이들.\n"
                + "그리고 마냥 아름답지만은 않은 그 시절의 기억들.\n"
                + "봄으로 가기 위한 그들의 성장통.\n"
                + "봄, 여름, 가을, 겨울... 사계절의 빛깔로 담아낸\n"
                + "풋풋하고 알싸한 아이들의 청춘 스토리.");
        UploadImage posterImage = new UploadImage("청춘 블라썸_커버.png", "청춘 블라썸_커버.png");
        UploadImage horizontalImage = new UploadImage("청춘 블라썸_커버.png", "청춘 블라썸_커버.png");
        WebtoonThumbnail webtoonThumbnail = WebtoonThumbnail.create(posterImage, horizontalImage);
        Webtoon webtoon = Webtoon.createWebtoon(member, createWebtoonReq, webtoonGenres, webtoonThumbnail);
        em.persist(webtoon);

    }

    public void initWebtoon2() {
        Member member = memberRepository.findOne(Long.valueOf(1));

        CreateWebtoonReq createWebtoonReq = new CreateWebtoonReq();
        createWebtoonReq.setName("세레나");
        createWebtoonReq.setWebtoonType(WebtoonType.STORY);
        createWebtoonReq.setTags(new ArrayList<>());

        List<GenreEnum> genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.ROMANCE);
        genreEnums.add(GenreEnum.DRAMA);

        List<Genre> genres = new ArrayList<>();
        for (GenreEnum genreEnum : genreEnums) {
            genres.add(genreRepository.findByGenreEnum(genreEnum));
        }

        List<WebtoonGenre> webtoonGenres = new ArrayList<>();
        for (Genre genre : genres) {
            webtoonGenres.add(WebtoonGenre.create(genre));
        }

        createWebtoonReq.setOneLineSummary("너는 절대로 내 세레니티의 주인이 될 수 없어.");
        createWebtoonReq.setSummary("세레니티 가문의 유일한 상속자가 된 세레나.\n"
                + "위태로워진 가문을 살리기 위해, 그녀의 할머니는 그녀를 왕국에서 가장 증오하는 남자와 결혼시켜버렸다.\n"
                + "무너져가던 세레나를 위로할 수 있는 건 오직 그녀의 애첩 프리드릭 뿐.\n"
                + "아름답고 찬란했던 세레니티의 그 여름날, 뜨거운 증오와 사랑으로 얽혀버린 세 사람의 이야기.");
        UploadImage posterImage = new UploadImage("webtoon-cover-sample.png", "webtoon-cover-sample.png");
        UploadImage horizontalImage = new UploadImage("webtoon-cover-sample.png", "webtoon-cover-sample.png");
        WebtoonThumbnail webtoonThumbnail = WebtoonThumbnail.create(posterImage, horizontalImage);
        Webtoon webtoon = Webtoon.createWebtoon(member, createWebtoonReq, webtoonGenres, webtoonThumbnail);
        em.persist(webtoon);
    }

    public Long save(Webtoon webtoon) {
        em.persist(webtoon);
        return webtoon.getId();
    }

    public Webtoon findByOne(Long id) {
        return em.find(Webtoon.class, id);
    }

    public List<Webtoon> findAllByMember(Member member) {
        return em.createQuery("select distinct w from Webtoon w"
                        + " join fetch w.webtoonThumbnail wt"
                        + " where w.member = :member", Webtoon.class)
                .setParameter("member", member)
                .getResultList();
    }
}
