package jh.naverwebtoon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateNestedCommentReq {
    @NotNull
    private Long roundId;
    @NotBlank
    @Size(min = 1, max = 500)
    private String content;
    @NotNull
    private Long commentId;
}
