package com.sf.bigscreen.config;

import com.sf.bigscreen.interceptor.TokenInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @Author: bi xuan
 * @Date: 2021/6/21 9:18
 * @Description:
 **/
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private TokenInterceptor tokenInterceptor;

    //构造方法
    public WebConfiguration(TokenInterceptor tokenInterceptor){
        this.tokenInterceptor = tokenInterceptor;
    }

    //配置登录拦截
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer){
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(3)));
        configurer.setDefaultTimeout(30000);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> excludePath = new ArrayList<>();
        //排除拦截，除了注册登录(此时还没token)，其他都拦截
//        excludePath.add("/user/register");  //登录
        excludePath.add("/user/login");     //注册
//        excludePath.add("/router/updateRoutes");
//        excludePath.add("/user/getUserInfo");
//        excludePath.add("/router/getList");
        excludePath.add("/static/**");  //静态资源
        excludePath.add("/assets/**");  //静态资源

        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    //配置跨域
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {

            //配置跨域
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")     //允许的路径
                        .allowedMethods("*")     //允许的方法
//                        .allowedOriginPatterns("*")       //允许的网站
                        .allowedOrigins("*")
                        .allowedHeaders("*")     //允许的请求头
                        .allowCredentials(true)
                        .maxAge(3600);
            }
        };
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:9527");
        config.addAllowedOrigin("http://192.168.7.219:9527");
        config.addAllowedOrigin("null");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config); // CORS 配置对所有接口都有效
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
