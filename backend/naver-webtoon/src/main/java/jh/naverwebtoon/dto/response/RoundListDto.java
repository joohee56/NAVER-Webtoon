package jh.naverwebtoon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoundListDto {
    private Long roundId;
    private String thumbnail;
    private Long roundNumber;
    private String title;
    @JsonFormat(pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    private Long totalLikeCount;
}
