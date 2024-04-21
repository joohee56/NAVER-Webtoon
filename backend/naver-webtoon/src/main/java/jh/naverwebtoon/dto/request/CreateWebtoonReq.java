package jh.naverwebtoon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank
    @Size(min = 1, max = 10)
    private String name;
    @NotNull
    private WebtoonCategory webtoonCategory;
    @NotEmpty
    private List<GenreEnum> genres;
    private List<String> tags;
    @NotBlank
    @Size(min = 1, max = 10)
    private String oneLineSummary;
    @NotBlank
    @Size(min = 1, max = 400)
    private String summary;
    private MultipartFile posterImage;
    private MultipartFile horizontalImage;
}
