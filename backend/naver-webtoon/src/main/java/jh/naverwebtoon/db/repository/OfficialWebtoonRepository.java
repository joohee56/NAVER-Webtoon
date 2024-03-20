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
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
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

        initOfficialWebtoon(Long.valueOf(3), "청춘 블라썸", WebtoonType.EPISODE, genreEnums, "푸르지 않아도 괜찮아!", "이제 막 청춘을 시작하고 있는 아이들.\n"
                + "그리고 마냥 아름답지만은 않은 그 시절의 기억들.\n"
                + "봄으로 가기 위한 그들의 성장통.\n"
                + "봄, 여름, 가을, 겨울... 사계절의 빛깔로 담아낸\n"
                + "풋풋하고 알싸한 아이들의 청춘 스토리.", "청춘 블라썸_커버.png", DayOfWeek.FRIDAY);

        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.ROMANCE);
        initOfficialWebtoon(Long.valueOf(2), "세레나", WebtoonType.STORY, genreEnums, "너는 절대로 내 세레니티의 주인이 될 수 없어.", "세레니티 가문의 유일한 상속자가 된 세레나.\n"
                + "위태로워진 가문을 살리기 위해, 그녀의 할머니는 그녀를 왕국에서 가장 증오하는 남자와 결혼시켜버렸다.\n"
                + "무너져가던 세레나를 위로할 수 있는 건 오직 그녀의 애첩 프리드릭 뿐.\n"
                + "아름답고 찬란했던 세레니티의 그 여름날, 뜨거운 증오와 사랑으로 얽혀버린 세 사람의 이야기.", "webtoon-cover-sample.png", DayOfWeek.SATURDAY);

        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.EMOTION);
        genreEnums.add(GenreEnum.ROMANCE);
        initOfficialWebtoon(Long.valueOf(4), "소녀의 세계", WebtoonType.STORY, genreEnums, "", "완벽해 보이지만 사실 외로웠던 백조들과 맘씨 착한 오리가 만나\n"
                + "여러 갈등을 함께 겪으며 진짜 친구가 되어가는 소녀들의 찐 우정물", "소녀의 세계_커버.png", DayOfWeek.MONDAY);

        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.EMOTION);
        initOfficialWebtoon(Long.valueOf(4), "괜찮니 얘들아?", WebtoonType.STORY, genreEnums, "", "본 컨텐츠는 보건복지부와 중앙자살예방센터의 브랜드 웹툰으로\n"
                + "모랑지 작가가 전하는 괜찮니? 캠페인 입니다.", "괜찮니 얘들아_커버.png", DayOfWeek.MONDAY);

        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.DAILY_LIFE);
        initOfficialWebtoon(Long.valueOf(2), "그냥 선생님", WebtoonType.EPISODE , genreEnums, "선생님 집은 학교 아니에요? 선생님도 집이 있어요?", "우리들 모두의 학창 시절, 언제나 그 자리에 있을 거만 같은 선생님들.\n"
                + "수업이 끝난 교실, 학교 바깥. 그냥 선생님들의 평범하고 사랑스러운 일상,\n"
                + "\n"
                + "일상 로맨스 <그냥 선생님>", "그냥 선생님_커버.png", DayOfWeek.TUESDAY);

        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.ROMANCE);
        genreEnums.add(GenreEnum.FANTASY);
        initOfficialWebtoon(Long.valueOf(3), "베이비 폭군", WebtoonType.STORY, genreEnums, "", "가질 수 없었던 화목한 가정, 다정한 부모님.\n"
                + "비참한 결말 끝에 눈을 떠 보니, 환생했다.\n"
                + "그것도 제국의 공주로!", "베이비 폭군_커버.png", DayOfWeek.WEDNESDAY);

        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.ROMANCE);
        initOfficialWebtoon(Long.valueOf(4), "사내연애 사절!", WebtoonType.STORY , genreEnums, "", "첫사랑에서 친구로, 친구에서 동료로,\n"
                + "역행하는 관계 속에서 솔직한 내 마음은 '사내연애 사절!'", "사내연애 사절_커버.png", DayOfWeek.THURSDAY);

        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.ROMANCE);
        genreEnums.add(GenreEnum.DRAMA);
        initOfficialWebtoon(Long.valueOf(2), "시월드가 내게 집착한다", WebtoonType.STORY, genreEnums, "", "사랑했던 가족들이 나를 죽였다.\n"
                + "과거로 돌아온 나는 내 목숨과 유산을 지키기로 결심하고 라피레온 대공을 찾아갔다.\n"
                + "그런데 이 대공가, 심상치 않다.\n"
                + "\n"
                + "무사히 복수를 마치고, 이혼할 수 있을까?", "시월드가 내게 집착한다_커버.png", DayOfWeek.SUNDAY);

        //도전만화 등록
        genreEnums = new ArrayList<>();
        genreEnums.add(GenreEnum.EMOTION);
        genreEnums.add(GenreEnum.DAILY_LIFE);
        initWebtoon(Long.valueOf(5), "오직 나의 이름으로", WebtoonType.EPISODE, genreEnums, "", "내 이름은 딱지다. 내 왼쪽 가슴의 휘장 때문이다. "
                + "‘RN: Registered Nurse (공인 등록 간호사)’ 간호사 국가고시에 합격한 정식 간호사를 상징하는 휘장. "
                + "‘RN’마크가 달린 휘장은 스스로에게 자부심과 자긍심을 느끼게도 하지만 때론, 그 휘장이 주홍글씨처럼 왼쪽 가슴에 박혀 딱지로 느껴질 때가 있다. "
                + "무조건적인 헌신과 희생을 바라는 사람들. 그리고 내가 아닌 다른 무언가로 불려지는 순간들. "
                + "‘RN'휘장과 'RN'딱지의 경계를 넘나드는 '정신병원’ 신규시절 딱지 간호사의 일상은 과연 어떨까?", "오직 나의 이름으로_커버.png");
    }

    public void initOfficialWebtoon(Long memberId, String name, WebtoonType webtoonType, List<GenreEnum> genreEnums, String oneLineSummary, String summary, String storeFileName, DayOfWeek dayOfWeek)
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

    public void initWebtoon(Long memberId, String name, WebtoonType webtoonType, List<GenreEnum> genreEnums, String oneLineSummary, String summary, String storeFileName) throws IOException {
        Member member = memberRepository.findOne(memberId);
        CreateWebtoonReq createWebtoonReq = new CreateWebtoonReq(name, webtoonType, new ArrayList<>(), genreEnums, oneLineSummary, summary, null, null);
        List<Genre> genres = createWebtoonReq.getGenres().stream()
                .map(genreEnum -> genreRepository.findByGenreEnum(genreEnum))
                .collect(Collectors.toList());
        WebtoonThumbnail webtoonThumbnail = WebtoonThumbnail.create(new UploadImage(storeFileName, storeFileName), new UploadImage(storeFileName, storeFileName));
        Webtoon webtoon = Webtoon.create(member, createWebtoonReq, genres, webtoonThumbnail);
        em.persist(webtoon);
    }

    public void save(OfficialWebtoon officialWebtoon) {
        em.persist(officialWebtoon);
    }

    public OfficialWebtoon findOne(Long id) {
        return em.find(OfficialWebtoon.class, id);
    }

    public List<OfficialWebtoon> findAll() {
        return em.createQuery("select distinct ow from OfficialWebtoon ow"
                        + " join fetch ow.member m"
                        + " join fetch ow.webtoonThumbnail wt", OfficialWebtoon.class)
                .getResultList();
    }

    public OfficialWebtoon findOneByIdWithMemberAndThumbnail(Long webtoonId) {
        return em.createQuery("select distinct ow from OfficialWebtoon ow"
                        + " join fetch ow.member m "
                        + " join fetch m.profileImage"
                        + " join fetch ow.webtoonThumbnail wt"
                        + " where ow.id = :webtoonId", OfficialWebtoon.class)
                .setParameter("webtoonId", webtoonId )
                .getSingleResult();
    }
}
