package org.example.filter;


import lombok.extern.slf4j.Slf4j;
import org.example.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
@Slf4j
public class AuthFilter implements Filter {

    @Value("${file.external-url}")
    private String externalUrl;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String uri = req.getRequestURI();

        if ("/".equals(uri)) {
            String url = externalUrl + "/index.html";
            log.info("跳转地址:"+url);
            res.sendRedirect(url);
            return;
        }

        // 放行登录页、静态资源、登录接口
        if (uri.endsWith("login.html") || uri.startsWith("/noToken") || uri.startsWith("/static") || uri.endsWith("index.html")
        || uri.contains("/file") || uri.endsWith("order-index.html") || uri.endsWith("order-mobile.html") || uri.startsWith("/order")
        ) {
            chain.doFilter(request, response);
            return;
        }

        // 校验 Token
        String token = req.getHeader("Authorization");
        if (token == null || !JwtUtil.verifyToken(token)) {
            res.setContentType("application/json;charset=UTF-8");
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("{\"message\":\"未登录或登录已过期\"}");
            return;
        }

        chain.doFilter(request, response);
    }
}
