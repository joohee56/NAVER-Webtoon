package jh.naverwebtoon.web.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jh.naverwebtoon.db.domain.enums.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("jh.naverwebtoon.web.controller")
@Slf4j
@RequiredArgsConstructor
public class ControllerAdvice {
    private final MessageSource messageSource;

    @Data
    @AllArgsConstructor
    public class ErrorResult {
        private ExceptionCode code;
        private Object message;
    }

    /**
     * 비지니스 로직 상 예외 처리
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ErrorResult illegalxxxExHandle(Exception e){
        log.info("예외 발생={}", e.getClass());
        return new ErrorResult(ExceptionCode.BUSINESS, e.getMessage());
    }

    /**
     * 검증 예외 처리
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResult validationExHandle(MethodArgumentNotValidException e){
        log.info("예외 발생={}", e.getClass());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> {
                    errors.put(((FieldError) c).getField(), getErrorMessage(c));
                });
        return new ErrorResult(ExceptionCode.VALIDATION, errors);
    }

    /**
     * 입력값 타입 예외 처리
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResult handleTypeMismatchExceptions(HttpMessageNotReadableException e){
        log.info("예외 발생={}", e.getClass());

        Map<String, String> errors = new HashMap<>();

        Pattern errorFieldPattern = Pattern.compile("\\[[\"](.*?)[\"]\\]");
        Matcher errorFieldMatcher = errorFieldPattern.matcher(e.getCause().getMessage());
        String errorField = errorFieldMatcher.find() ? errorFieldMatcher.group(1) : "FAIL";

        Pattern rightTypePattern = Pattern.compile("[`](.*?)[`]");
        Matcher rightTypeMatcher = rightTypePattern.matcher(e.getMessage());
        String rightType = rightTypeMatcher.find() ? rightTypeMatcher.group(1) : "?";

        String errorMessage = messageSource.getMessage("typeMismatch", new Object[] {rightType}, Locale.KOREA);
        errors.put(errorField, errorMessage);

        log.error(e.toString());

        return new ErrorResult(ExceptionCode.VALIDATION, errors);
    }

    /**
     * errors.properties에 등록된 예외 메세지 가져오기
     */
    private String getErrorMessage(ObjectError error) {
        String[] codes = error.getCodes();
        for (String code : codes) {
            try {
                return messageSource.getMessage(code, error.getArguments(), Locale.KOREA);
            } catch (NoSuchMessageException ignored) {}
        }
        return error.getDefaultMessage();
    }
}
