package jh.naverwebtoon.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import jh.naverwebtoon.db.domain.Round;
import lombok.Data;

@Data
public class RoundListDto {
    private Long roundId;
    private String thumbnail;
    private Long roundNumber;
    private String title;
    @JsonFormat(pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public static RoundListDto create(Round round) {
        RoundListDto roundListDto = new RoundListDto();
        roundListDto.roundId = round.getId();
        roundListDto.thumbnail = round.getRoundThumbnail().getThumbnail().getStoreFileName();
        roundListDto.roundNumber = round.getRoundNumber();
        roundListDto.title = round.getRoundTitle();
        roundListDto.createdAt = round.getCreatedAt();
        return roundListDto;
    }
}
