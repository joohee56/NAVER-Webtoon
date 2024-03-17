package jh.naverwebtoon.db.domain.enums;

public enum GenreEnum {
    ROMANCE("로맨스"), FANTASY("판타지"), ACTION("액션"), DAILY_LIFE("일상"), THRILLER("스릴러"), COMEDY("개그"), MARTIAL_ARTS_AND_HISTORICAL("무협/사극"), DRAMA("드라마"), EMOTION("감성"), SPORTS("스포츠");

    private String title;

    GenreEnum(String title) {
        this.title = title;
    }
}
