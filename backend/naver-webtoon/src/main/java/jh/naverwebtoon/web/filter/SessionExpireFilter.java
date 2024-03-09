package jh.naverwebtoon.web.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionExpireFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        long serverTime = System.currentTimeMillis();
        long sessionExpiryTime = serverTime + httpServletRequest.getSession().getMaxInactiveInterval()*1000;
        Cookie cookie = new Cookie("sessionExpiry", String.valueOf(sessionExpiryTime));
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        chain.doFilter(request, response);
    }
}
