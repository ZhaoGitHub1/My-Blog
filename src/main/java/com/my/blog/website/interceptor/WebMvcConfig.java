package com.my.blog.website.interceptor;


import com.my.blog.website.utils.TaleUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;

/**
 * 向mvc中添加自定义组件
 * Created by BlueT on 2017/3/9.
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Resource
    private BaseInterceptor baseInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseInterceptor)
                //告知拦截器：/static/admin/** 与 /static/user/** 不需要拦截 （配置的是 路径）
                .excludePathPatterns("/static/admin/**", "/static/user/**");
    }

    /**
     * 添加静态资源文件，外部可以直接访问地址
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ TaleUtils.getUploadFilePath()+"upload/");
        //需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
