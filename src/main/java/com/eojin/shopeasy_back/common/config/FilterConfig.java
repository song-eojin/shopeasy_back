package com.eojin.shopeasy_back.common.config;

import com.eojin.shopeasy_back.common.filter.CorsLoggingFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<CorsLoggingFilter> corsLoggingFilter() {
        FilterRegistrationBean<CorsLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsLoggingFilter());
        registrationBean.addUrlPatterns("/*"); // 모든 URL 패턴에 대해 필터 적용
        return registrationBean;
    }
}