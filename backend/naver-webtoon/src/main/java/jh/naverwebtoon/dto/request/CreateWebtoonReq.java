package jh.naverwebtoon.dto.request;

import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@Setter
@NoArgsConstructor
public class CreateWebtoonReq {
    private String name;
    private WebtoonCategory webtoonCategory;
    private List<String> tags;
    private List<GenreEnum> genres;
    private String oneLineSummary;
    private String summary;
    private MultipartFile posterImage;
    private MultipartFile horizontalImage;
}
