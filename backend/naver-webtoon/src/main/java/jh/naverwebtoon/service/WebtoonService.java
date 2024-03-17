package jh.naverwebtoon.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jh.naverwebtoon.db.domain.Genre;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.UploadImage;
import jh.naverwebtoon.db.domain.WebtoonGenre;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.db.repository.GenreRepository;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.WebtoonRepository;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
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
    public Webtoon createWebtoon(Long id, CreateWebtoonReq createWebtoonReq) throws IOException {
        Member member = memberRepository.findOne(id);
        List<Genre> genres = new ArrayList<>();
        for (GenreEnum genreEnum : createWebtoonReq.getGenres()) {
            genres.add(genreRepository.findByGenreEnum(genreEnum));
        }

        List<WebtoonGenre> webtoonGenres = new ArrayList<>();
        for (Genre genre : genres) {
            webtoonGenres.add(WebtoonGenre.create(genre));
        }

        UploadImage posterImage = fileStore.storeFile(createWebtoonReq.getPosterImage());
        UploadImage horizontalImage = fileStore.storeFile(createWebtoonReq.getHorizontalImage());
        WebtoonThumbnail webtoonThumbnail = WebtoonThumbnail.create(posterImage, horizontalImage);

        Webtoon webtoon = Webtoon.createWebtoon(member, createWebtoonReq, webtoonGenres, webtoonThumbnail);
        Long webtoonId = webtoonRepository.save(webtoon);
        return webtoonRepository.findByOne(webtoonId);
    }
}
