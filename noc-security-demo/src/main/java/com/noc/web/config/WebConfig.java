package com.noc.web.config;

import com.noc.web.filter.TimeFilter;
import com.noc.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

// 告诉spring这个类是一个配置类
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @SuppressWarnings("unused")
    @Autowired
    private TimeInterceptor timeInterceptor;

    /**
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(timeInterceptor);
    }

    /**
     * 和web.xml中配置filter作用一样
     *
     * @return
     */
//    @Bean
    public FilterRegistrationBean timeFilter() {

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        // 配置在哪些url中起作用
        List<String> urls = new ArrayList<>();
        // 所有的路径都起作用
        urls.add("/*");
        registrationBean.setUrlPatterns(urls);

        return registrationBean;

    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
        // 针对Callable注册拦截器
//        configurer.registerCallableInterceptors()
        // 针对DeferredResult注册拦截器
//        configurer.registerDeferredResultInterceptors()
        // 异步请求默认超时时间
//        configurer.setDefaultTimeout()
        // 可以替代spring的简单线程池（spring其实不是提供一个真正的线程池）
//        configurer.setTaskExecutor()
    }
}