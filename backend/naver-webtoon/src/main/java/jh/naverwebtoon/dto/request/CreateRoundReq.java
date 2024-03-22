package jh.naverwebtoon.dto.request;

import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateRoundReq {
    private Long webtoonId;
    private String roundTitle;
    private MultipartFile thumbnail;
    private List<MultipartFile> manuscripts;
    private MultipartFile mergeManuscript;
    private String authorNote;
}
