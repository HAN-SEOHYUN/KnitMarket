package com.proj.KnitMarket.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Slf4j
public class LoggerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        String msg = "", url = "";
        if (session == null || session.getAttribute("id") == null) {
            log.info("로그인 필요");

            msg ="로그인 후 이용가능한 서비스입니다";
            url = "location.href = '" + request.getContextPath() + "/knitmarket/login';";
            log.info("url={}",url);

            log.info("현재 위치 : " + request.getRequestURI());
            String requestURI = request.getRequestURI();
            requestURI = requestURI.substring(requestURI.indexOf("/", 2));

            response.setContentType("text/html; charset = UTF-8");
            PrintWriter out = response.getWriter();
            out.print("<script type='text/javascript'>");
            out.print("alert('" + msg + "');");
            out.print(url);
            out.print("</script>");
        }

        return true;

    }
}
