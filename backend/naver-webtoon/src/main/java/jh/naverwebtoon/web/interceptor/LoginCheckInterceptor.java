package jh.naverwebtoon.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jh.naverwebtoon.web.Auth;
import jh.naverwebtoon.web.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String requestURI = request.getRequestURI();
        String requestMethod = request.getMethod();

        log.info("인증 체크 인터셉터 실행 {}, {}", requestURI, requestMethod);
        if (requestMethod.equals("OPTIONS")) {
            log.info("axios preflight에 관한 인증 체크 패스");
            return true;
        }

        //메소드 권한 체크
        HandlerMethod method = (HandlerMethod) handler;
        Auth auth = method.getMethodAnnotation(Auth.class);
        if (auth == null) {
            log.info("인증이 필요없는 요청이므로 인증 체크 패스");
            return true;
        }

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER_ID) == null) {
            log.info("미인증 사용자 오류");
            response.sendError(401, "로그인이 필요한 사용자입니다.");
            return false;
        }
        return true;
    }
}
