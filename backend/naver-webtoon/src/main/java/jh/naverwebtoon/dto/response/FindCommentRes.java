package jh.naverwebtoon.dto.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindCommentRes {
    private Long totalCommentCount;
    private List<CommentDto> comments;
}
