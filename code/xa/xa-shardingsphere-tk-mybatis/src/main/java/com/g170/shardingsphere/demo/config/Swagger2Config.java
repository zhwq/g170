package com.g170.shardingsphere.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("shardingsphere jdbc").build();
  }
  @Bean public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .useDefaultResponseMessages(false)
      .select()
      .apis(requestHandler -> {
        Class<?> declaringClass = requestHandler.declaringClass();
        if (declaringClass.isAnnotationPresent(RestController.class)) return true;
        if (requestHandler.isAnnotatedWith(ResponseBody.class)) return true;
        return false;
      })
      .build();
  }
}
