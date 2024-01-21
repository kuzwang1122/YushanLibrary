package com.library.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.library.utils.PathUtils;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:5500/")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String winPath = PathUtils.getClassLoadRootPath() + "/src/main/resources/static/files/";

        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/files/**").
                addResourceLocations("file:" + winPath);
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}