package jh.naverwebtoon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.DayOfWeek;
import java.util.List;
import jh.naverwebtoon.db.domain.enums.GenreEnum;
import jh.naverwebtoon.db.domain.enums.WebtoonCategory;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateOfficialWebtoonReq {
    @NotBlank
    @Size(min = 1, max = 30)
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

    @NotNull
    @Range(min = 1)
    private Long authorId;
    @NotNull
    @Range(min = 0, max = 100)
    private int ageLimit;
    private DayOfWeek dayOfWeek;
}
