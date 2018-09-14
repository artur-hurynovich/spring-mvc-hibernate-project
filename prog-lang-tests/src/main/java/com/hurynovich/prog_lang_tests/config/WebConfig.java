package com.hurynovich.prog_lang_tests.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hurynovich.prog_lang_tests.controller.interceptor.AuthInterceptor;
import com.hurynovich.prog_lang_tests.controller.interceptor.EncodingInterceptor;

@Configuration
@EnableWebMvc
@ComponentScan("com.hurynovich.prog_lang_tests")
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	@Qualifier("encodingInterceptor")
	private EncodingInterceptor encodingInterceptor;
	
	@Autowired
	@Qualifier("authInterceptor")
	private AuthInterceptor authInterceptor;
	
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp().prefix("/WEB-INF/views/").suffix(".jsp");
	}
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
        .addResourceLocations("/resources/"); 
    }
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(encodingInterceptor);
        registry.addInterceptor(authInterceptor);
    }
}
