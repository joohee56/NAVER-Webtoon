package jh.naverwebtoon.db.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WebtoonRanking extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String rankingList;

    public static WebtoonRanking create(List<Long> rankings) {
        WebtoonRanking webtoonRanking = new WebtoonRanking();
        webtoonRanking.rankingList = parseToString(rankings);
        return webtoonRanking;
    }

    private static String parseToString(List<Long> rankings) {
        return rankings.stream()
                .map(id->String.valueOf(id)) //Long을 문자열로 변환
                .collect(Collectors.joining(",")); //각 문자열을 쉼표로 연결
    }
}
