package com.skipthedishes;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringfoxConfiguration {
  
  @Bean
  public Docket docket() {
    return new Docket(DocumentationType.SWAGGER_2)
    		.groupName("main")
    		.tags(new Tag("Cousine Entity", "Repository for Cousine entities"));
  }
  
}
