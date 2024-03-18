package jh.naverwebtoon.db.repository;

import jakarta.persistence.EntityManager;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.UploadImage;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class OfficialWebtoonRepository {
    private final EntityManager em;
    private final MemberRepository memberRepository;
    private final GenreRepository genreRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() throws IOException {
        List<GenreEnum> genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.ROMANCE);
        genreEnums.add(GenreEnum.EMOTION);

        initWebtoon(Long.valueOf(3), "청춘 블라썸", WebtoonType.EPISODE, genreEnums, "푸르지 않아도 괜찮아!", "이제 막 청춘을 시작하고 있는 아이들.\n"
                + "그리고 마냥 아름답지만은 않은 그 시절의 기억들.\n"
                + "봄으로 가기 위한 그들의 성장통.\n"
                + "봄, 여름, 가을, 겨울... 사계절의 빛깔로 담아낸\n"
                + "풋풋하고 알싸한 아이들의 청춘 스토리.", "청춘 블라썸_커버.png", DayOfWeek.FRIDAY);

        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.ROMANCE);
        initWebtoon(Long.valueOf(2), "세레나", WebtoonType.STORY, genreEnums, "너는 절대로 내 세레니티의 주인이 될 수 없어.", "세레니티 가문의 유일한 상속자가 된 세레나.\n"
                + "위태로워진 가문을 살리기 위해, 그녀의 할머니는 그녀를 왕국에서 가장 증오하는 남자와 결혼시켜버렸다.\n"
                + "무너져가던 세레나를 위로할 수 있는 건 오직 그녀의 애첩 프리드릭 뿐.\n"
                + "아름답고 찬란했던 세레니티의 그 여름날, 뜨거운 증오와 사랑으로 얽혀버린 세 사람의 이야기.", "webtoon-cover-sample.png", DayOfWeek.SATURDAY);

        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.EMOTION);
        genreEnums.add(GenreEnum.ROMANCE);
        initWebtoon(Long.valueOf(4), "소녀의 세계", WebtoonType.STORY, genreEnums, "", "완벽해 보이지만 사실 외로웠던 백조들과 맘씨 착한 오리가 만나\n"
                + "여러 갈등을 함께 겪으며 진짜 친구가 되어가는 소녀들의 찐 우정물", "소녀의 세계_커버.png", DayOfWeek.MONDAY);

        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.EMOTION);
        initWebtoon(Long.valueOf(4), "괜찮니 얘들아?", WebtoonType.STORY, genreEnums, "", "본 컨텐츠는 보건복지부와 중앙자살예방센터의 브랜드 웹툰으로\n"
                + "모랑지 작가가 전하는 괜찮니? 캠페인 입니다.", "괜찮니 얘들아_커버.png", DayOfWeek.MONDAY);
    }

    public void initWebtoon(Long memberId, String name, WebtoonType webtoonType, List<GenreEnum> genreEnums, String oneLineSummary, String summary, String storeFileName, DayOfWeek dayOfWeek)
            throws IOException {
        Member member = memberRepository.findOne(memberId);
        CreateWebtoonReq createWebtoonReq = new CreateWebtoonReq(name, webtoonType, new ArrayList<>(), genreEnums, oneLineSummary, summary, null, null);
        List<Genre> genres = createWebtoonReq.getGenres().stream()
                .map(genreEnum -> genreRepository.findByGenreEnum(genreEnum))
                .collect(Collectors.toList());
        WebtoonThumbnail webtoonThumbnail = WebtoonThumbnail.create(new UploadImage(storeFileName, storeFileName), new UploadImage(storeFileName, storeFileName));
        OfficialWebtoon officialWebtoon = new OfficialWebtoon(member, createWebtoonReq, genres, webtoonThumbnail, dayOfWeek);
        em.persist(officialWebtoon);
    }

    public void save(OfficialWebtoon officialWebtoon) {
        em.persist(officialWebtoon);
    }

    public OfficialWebtoon findOne(Long id) {
        return em.find(OfficialWebtoon.class, id);
    }
}
