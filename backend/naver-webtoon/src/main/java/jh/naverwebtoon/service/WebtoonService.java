package jh.naverwebtoon.service;

import java.util.ArrayList;
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
import jh.naverwebtoon.dto.response.FindCreateRoundInfoRes;
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
    public Webtoon createWebtoon(Long memberId, CreateWebtoonReq createWebtoonReq) {
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
        return webtoonRepository.findAllByMemberWithThumbnail(memberId);
    }

    public List<FindCreateRoundInfoRes> findCreateRoundInfo(Long memberId) {
        List<FindCreateRoundInfoRes> res = new ArrayList<>();
        List<Object[]> webtoons = webtoonRepository.findAllByMemberWithMaxRoundNumber(memberId);
        for (Object[] webtoon : webtoons) {
            res.add(FindCreateRoundInfoRes.create((Long) webtoon[0], (String) webtoon[1], webtoon[2]));
        }
        return res;
    }

}
