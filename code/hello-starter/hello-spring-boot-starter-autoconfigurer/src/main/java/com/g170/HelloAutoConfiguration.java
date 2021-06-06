package com.g170;

import com.g170.service.HelloStarterService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
public class HelloAutoConfiguration {

  @Bean public HelloStarterService helloStarterService() {
    HelloStarterService helloStarterService = new HelloStarterService();
    return helloStarterService;
  }
}
