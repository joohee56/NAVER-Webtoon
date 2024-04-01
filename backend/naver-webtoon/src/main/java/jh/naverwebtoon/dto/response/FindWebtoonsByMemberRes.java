package jh.naverwebtoon.dto.response;

import jh.naverwebtoon.db.domain.enums.WebtoonType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FindWebtoonsByMemberRes {
    private Long webtoonId;
    private String webtoonName;
    private String posterStoreName;
    private WebtoonType serialType;
    private Long totalCommentCount;

}
