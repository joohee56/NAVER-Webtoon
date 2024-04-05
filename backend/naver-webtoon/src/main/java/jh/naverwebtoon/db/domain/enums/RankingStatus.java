package jh.naverwebtoon.db.domain.enums;

public enum RankingStatus {
    UP("up"), DOWN("down"), UNCHANGING("unchanging");

    private String name;

    RankingStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
