package jh.naverwebtoon.db.domain.enums;

public enum SortingEnum {
    POPULARITY("인기순"),
    UPDATE("업데이트순"),
    FEMALE_POPULARITY("여성"),
    MALE_POPULARITY("남성");

    private String description;

    SortingEnum(String description) {
        this.description = description;
    }
}
