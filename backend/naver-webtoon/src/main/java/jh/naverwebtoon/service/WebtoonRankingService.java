package jh.naverwebtoon.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import jh.naverwebtoon.db.domain.WebtoonRanking;
import jh.naverwebtoon.db.domain.webtoon.Webtoon;
import jh.naverwebtoon.db.repository.WebtoonRankingRepository;
import jh.naverwebtoon.db.repository.WebtoonRepository;
import jh.naverwebtoon.dto.response.FindWebtoonRankingRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WebtoonRankingService {
    private final WebtoonRankingRepository webtoonRankingRepository;
    private final WebtoonRepository webtoonRepository;

    @Transactional
    public List<FindWebtoonRankingRes> findRanking() {
        //0. 최근 10회차 중 좋아요 누적 수가 가장 높은 5개의 웹툰 리스트 조회
        List findWebtoons = webtoonRankingRepository.findRankingsByRecentRounds();

        //1. 상승, 하강 비교를 위해 이전 웹툰 랭킹 조회
        List<WebtoonRanking> findLatestRanking = webtoonRankingRepository.findLatestOne();
        List<Long> nowRanking = extractIdFromListObject(findWebtoons);
        List<Long> latestRanking = extractIdFromWebtoonRanking(findLatestRanking);

        //2. 전과 결과가 다르다면 바뀐 랭킹 리스트 삽입
        boolean isChanging = checkRankingChange(nowRanking, latestRanking);
        if (isChanging) {
            webtoonRankingRepository.save(WebtoonRanking.create(nowRanking));
        }

        List<FindWebtoonRankingRes> findWebtoonRankingResList = new ArrayList<>();
        for (int ranking=0; ranking<findWebtoons.size(); ranking++) {
            Object[] field = (Object[]) findWebtoons.get(ranking);
            Long webtoonId = (Long) field[0];
            Long totalLikeCount = (Long) field[1];

            Webtoon webtoon = webtoonRepository.findOneWithThumbnailAndGenre(webtoonId);
            FindWebtoonRankingRes res = FindWebtoonRankingRes.create(webtoon, totalLikeCount, ranking, latestRanking);
            findWebtoonRankingResList.add(res);
        }

        return findWebtoonRankingResList;
    }

    private List<Long> extractIdFromListObject(List findWebtoons) {
        List<Long> idList = new ArrayList<>();
        for (Object webtoon : findWebtoons) {
            Object[] field = (Object[]) webtoon;
            idList.add((Long) field[0]);
        }
        return idList;
    }

    private List<Long> extractIdFromWebtoonRanking(List<WebtoonRanking> findLatestRanking) {
        if (findLatestRanking.isEmpty()) {
            return new ArrayList<>();
        } else {
            return Arrays.stream(findLatestRanking.get(0).getRankingList().split(","))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }
    }

    private static boolean checkRankingChange(List<Long> nowRanking, List<Long> latestRanking) {
        if (nowRanking.size() != latestRanking.size()) {
            return true;
        }

        for (int i=0; i<nowRanking.size(); i++) {
            if (nowRanking.get(i) != latestRanking.get(i)) {
                return true;
            }
        }

        return false;
    }
}
