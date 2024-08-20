package com.eojin.shopeasy_back.common.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/*")
public class CorsLoggingFilter implements jakarta.servlet.Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 초기화 로직 (필요 시)
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // CORS 요청 및 응답 헤더를 로그에 기록
        System.out.println("CORS Request Origin: " + httpRequest.getHeader("Origin"));
        System.out.println("CORS Request Method: " + httpRequest.getMethod());
        System.out.println("CORS Response Headers: ");
        httpResponse.getHeaderNames().forEach(headerName ->
                                                      System.out.println(headerName + ": " + httpResponse.getHeader(headerName))
        );

        // 체인 필터링 계속 진행
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // 정리 작업 (필요 시)
    }
}