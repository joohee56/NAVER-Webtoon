package jh.naverwebtoon.db.domain.enums;

public enum RankingEnum {
    FIRST, SECOND, THIRD, FOURTH, FIFTH, NONE;

    public static RankingEnum create(int ranking) {
        switch(ranking) {
            case 1: {
                return RankingEnum.FIRST;
            } case 2: {
                return RankingEnum.SECOND;
            } case 3: {
                return RankingEnum.THIRD;
            } case 4 :{
                return RankingEnum.FOURTH;
            } case 5: {
                return RankingEnum.FIFTH;
            }
        }

        return RankingEnum.NONE;
    }
}
