package jh.naverwebtoon.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.UploadImage;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.db.repository.GenreRepository;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.WebtoonRepository;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
import jh.naverwebtoon.dto.response.FindWebtoonsByMemberRes;
import jh.naverwebtoon.util.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WebtoonService {
    private final WebtoonRepository webtoonRepository;
    private final MemberRepository memberRepository;
    private final GenreRepository genreRepository;
    private final FileStore fileStore;

    @Transactional
    public Webtoon createWebtoon(Long memberId, CreateWebtoonReq createWebtoonReq) throws IOException {
        Member member = memberRepository.findOne(memberId);
        List<Genre> genres = createWebtoonReq.getGenres().stream()
                .map(genreEnum -> genreRepository.findByGenreEnum(genreEnum))
                .collect(Collectors.toList());

        UploadImage posterImage = fileStore.storeFile(createWebtoonReq.getPosterImage());
        UploadImage horizontalImage = fileStore.storeFile(createWebtoonReq.getHorizontalImage());
        WebtoonThumbnail webtoonThumbnail = WebtoonThumbnail.create(posterImage, horizontalImage);

        Webtoon webtoon = Webtoon.create(member, createWebtoonReq, genres, webtoonThumbnail);
        Long webtoonId = webtoonRepository.save(webtoon);
        return webtoonRepository.findOne(webtoonId);
    }

    public List<FindWebtoonsByMemberRes> findAllByMember(Long memberId) {
        Member member = memberRepository.findOne(memberId);
        List<Webtoon> webtoons = webtoonRepository.findAllByMember(member);
        return webtoons.stream()
                .map(w -> FindWebtoonsByMemberRes.create(w))
                .collect(Collectors.toList());
    }
}
