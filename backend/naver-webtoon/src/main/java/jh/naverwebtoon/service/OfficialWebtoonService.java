package jh.naverwebtoon.service;

import java.io.IOException;
import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.UploadImage;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.db.repository.GenreRepository;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.OfficialWebtoonRepository;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonDetailRes;
import jh.naverwebtoon.dto.response.FindOfficialWebtoonsRes;
import jh.naverwebtoon.util.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OfficialWebtoonService {
    private final OfficialWebtoonRepository officialWebtoonRepository;
    private final MemberRepository memberRepository;
    private final GenreRepository genreRepository;
    private final FileStore fileStore;

    @Transactional
    public void createOfficialWebtoon(Long memberId, CreateWebtoonReq createWebtoonReq, DayOfWeek dayOfWeek) throws IOException {
        Member member = memberRepository.findOne(memberId);
        List<Genre> genres = createWebtoonReq.getGenres().stream()
                .map(genreEnum -> genreRepository.findByGenreEnum(genreEnum))
                .collect(Collectors.toList());

        UploadImage posterImage = fileStore.storeFile(createWebtoonReq.getPosterImage());
        UploadImage horizontalImage = fileStore.storeFile(createWebtoonReq.getHorizontalImage());
        WebtoonThumbnail webtoonThumbnail = WebtoonThumbnail.create(posterImage, horizontalImage);

        OfficialWebtoon officialWebtoon = new OfficialWebtoon(member, createWebtoonReq, genres, webtoonThumbnail, dayOfWeek);
        officialWebtoonRepository.save(officialWebtoon);
    }

    public List<FindOfficialWebtoonsRes> findAllOfficialWebtoon() {
        return officialWebtoonRepository.findAll().stream()
                .map(officialWebtoon -> FindOfficialWebtoonsRes.create(officialWebtoon))
                .collect(Collectors.toList());
    }

    public FindOfficialWebtoonDetailRes findOfficialWebtoonDetail(Long webtoonId) {
        OfficialWebtoon officialWebtoon = officialWebtoonRepository.findOneByIdWithMemberAndThumbnail(
                webtoonId);
        return FindOfficialWebtoonDetailRes.create(officialWebtoon);
    }
}
