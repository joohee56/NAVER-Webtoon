package jh.naverwebtoon.db.domain.enums;

public enum RankingEnum {
    FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, SEVENTH, EIGHTH, NINTH, TENTH, NONE;

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
            } case 6: {
                return RankingEnum.SIXTH;
            } case 7: {
                return RankingEnum.SEVENTH;
            } case 8: {
                return RankingEnum.EIGHTH;
            } case 9: {
                return RankingEnum.NINTH;
            } case 10: {
                return RankingEnum.TENTH;
            }
        }

        return RankingEnum.NONE;
    }
}
