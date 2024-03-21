package jh.naverwebtoon.dto.request;

import java.util.List;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateRoundReq {
    private Long webtoonId;
    private String roundName;
    private MultipartFile thumnail;
    private List<MultipartFile> manuscipts;
    private String authorNote;
}
