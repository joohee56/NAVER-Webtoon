package jh.naverwebtoon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoundReq {
    @NotNull
    private Long webtoonId;
    @NotBlank
    @Size(min = 1, max = 35)
    private String roundTitle;
    @NotNull
    private MultipartFile thumbnail;
    @NotEmpty
    private List<MultipartFile> manuscripts;
    @NotNull
    private MultipartFile mergeManuscript;
    @NotBlank
    @Size(min = 1, max = 100)
    private String authorNote;
}
