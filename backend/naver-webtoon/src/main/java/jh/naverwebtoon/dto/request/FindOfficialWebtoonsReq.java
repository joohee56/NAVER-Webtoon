package jh.naverwebtoon.dto.request;

import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FindOfficialWebtoonsReq {
    private List<GenreEnum> genres;
//    private List<GenreEnum> genreEnums;
//
//    public FindOfficialWebtoonsReq(List<String> genres) {
//        this.genres = genres;
//        this.genreEnums = genres.stream()
//                .map(genre -> GenreEnum.valueOf(genre))
//                .collect(Collectors.toList());
//    }
}
