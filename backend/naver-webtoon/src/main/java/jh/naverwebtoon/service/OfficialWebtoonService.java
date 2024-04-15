package jh.naverwebtoon.service;

import java.time.DayOfWeek;
import java.util.List;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.UploadImage;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.OfficialWebtoonRepository;
import jh.naverwebtoon.dto.request.CreateWebtoonReq;
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
    private final FileStore fileStore;

    @Transactional
    public void createOfficialWebtoon(Long memberId, CreateWebtoonReq createWebtoonReq, DayOfWeek dayOfWeek) {
        Member member = memberRepository.findOne(memberId);
        UploadImage posterImage = fileStore.storeFile(createWebtoonReq.getPosterImage());
        UploadImage horizontalImage = fileStore.storeFile(createWebtoonReq.getHorizontalImage());
        WebtoonThumbnail webtoonThumbnail = WebtoonThumbnail.create(posterImage, horizontalImage);

        OfficialWebtoon officialWebtoon = new OfficialWebtoon(member, createWebtoonReq, webtoonThumbnail, dayOfWeek);
        officialWebtoonRepository.save(officialWebtoon);
    }

    /**
     * 전체 웹툰 리스트 조회
     * 인기순 / 업데이트 순으로 정렬
     */
    public List<FindOfficialWebtoonsRes> findAll(SortingEnum sorting) {
        if (sorting == SortingEnum.POPULARITY) {
            return officialWebtoonRepository.findAllOrderByPopularity();
        } else {  //UPDATE
            return officialWebtoonRepository.findAllOrderByUpdate();
        }
    }


}
