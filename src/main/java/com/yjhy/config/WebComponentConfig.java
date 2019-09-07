package com.yjhy.config;

import com.yjhy.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by husong on 2018/10/24.
 */
@Configuration
public class WebComponentConfig {
    @Bean
    public FilterRegistrationBean someFilterRegistration1() {
        String[] staticPaths = {"/css/", "/img/", "/js/", "/layui/","/angular/"};
        String[] excludePaths = {"/login","/loginVerify"};
        String[] excludeSuffix = {".js", ".css", "gif", "png", "gif", ".ttf"};
        //新建过滤器注册类
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // 添加我们写好的过滤器
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setStaticPaths(staticPaths);
        loginFilter.setExcludePaths(excludePaths);
        registration.setFilter(loginFilter);
        // 设置过滤器的URL模式
        registration.addUrlPatterns("/*");
        return registration;
    }

}
