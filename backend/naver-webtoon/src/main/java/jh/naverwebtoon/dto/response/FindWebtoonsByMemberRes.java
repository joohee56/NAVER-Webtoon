package jh.naverwebtoon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
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
    @JsonFormat(pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

}
