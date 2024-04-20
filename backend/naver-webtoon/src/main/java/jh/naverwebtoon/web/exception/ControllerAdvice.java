package jh.naverwebtoon.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("jh.naverwebtoon.web.controller")
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public String illegalxxxExHandle(Exception e){
        return e.getMessage();
    }


//    @AllArgsConstructor
//    @Data
//    public class ErrorResult {
//        private String code;
//        private String message;
//    }
}
