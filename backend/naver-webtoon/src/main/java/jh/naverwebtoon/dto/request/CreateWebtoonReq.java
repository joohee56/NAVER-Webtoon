package jh.naverwebtoon.dto.request;

import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateWebtoonReq {
    private String name;
    private WebtoonType webtoonType;
    private List<String> tags;
    private List<GenreEnum> genres;
    private String oneLineSummary;
    private String summary;
    private MultipartFile posterImage;
    private MultipartFile horizontalImage;
}
