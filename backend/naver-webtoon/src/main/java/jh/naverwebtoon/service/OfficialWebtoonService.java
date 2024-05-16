package jh.naverwebtoon.service;

import java.util.List;
import jh.naverwebtoon.db.domain.Member;
import jh.naverwebtoon.db.domain.WebtoonThumbnail;
import jh.naverwebtoon.db.domain.enums.SortingEnum;
import jh.naverwebtoon.db.domain.webtoon.OfficialWebtoon;
import jh.naverwebtoon.db.repository.MemberRepository;
import jh.naverwebtoon.db.repository.OfficialWebtoonRepository;
import jh.naverwebtoon.dto.request.CreateOfficialWebtoonReq;
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
    public Long createOfficialWebtoon(Long memberId, CreateOfficialWebtoonReq createOfficialWebtoonReq) {
        Member member = memberRepository.findOne(createOfficialWebtoonReq.getAuthorId());
        if(member == null) {
            throw new IllegalStateException("작가 아이디를 찾을 수 없습니다.");
        }
        WebtoonThumbnail webtoonThumbnail = fileStore.createWebtoonThumbnail(createOfficialWebtoonReq.getPosterImage(),
                createOfficialWebtoonReq.getHorizontalImage());
        OfficialWebtoon officialWebtoon = new OfficialWebtoon(member, createOfficialWebtoonReq, webtoonThumbnail);
        return officialWebtoonRepository.save(officialWebtoon);
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
