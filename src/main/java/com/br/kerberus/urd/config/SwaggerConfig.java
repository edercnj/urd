package com.br.kerberus.urd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig  extends WebMvcConfigurationSupport{                                    
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.br.kerberus.urd.controller"))
	      .paths(PathSelectors.ant("/api/**"))
	      .build()
	      .useDefaultResponseMessages(false)
	      .apiInfo(apiInfo());
	}
	 
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "Urd API", 
	      "Distributed log management",
	      "1.0",
	      "https://github.com/edercnj/urd/blob/master/LICENSE",
	      new Contact("Eder Nunes", "https://github.com/edercnj", "edercnj@gmail.com"),
	      "License of API", "https://github.com/edercnj/urd/blob/master/LICENSE", Collections.emptyList());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    registry.addResourceHandler("swagger-ui.html")
	      .addResourceLocations("classpath:/META-INF/resources/");
	 
	    registry.addResourceHandler("/webjars/**")
	      .addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}
