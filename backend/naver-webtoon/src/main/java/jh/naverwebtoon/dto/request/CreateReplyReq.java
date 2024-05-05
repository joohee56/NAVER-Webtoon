package jh.naverwebtoon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateReplyReq {
    @NotNull
    private Long roundId;
    @NotBlank
    @Size(min = 1, max = 500)
    private String replyContent;
    @NotNull
    private Long commentId;
}
