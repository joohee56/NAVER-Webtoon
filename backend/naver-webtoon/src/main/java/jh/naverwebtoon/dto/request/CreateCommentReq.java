package jh.naverwebtoon.dto.request;

import lombok.Data;

@Data
public class CreateCommentReq {
    private Long roundId;
    private String content;
}
