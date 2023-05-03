package com.mpr.cursobatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class EnvConfig {

  @Bean
  public static PropertySourcesPlaceholderConfigurer config() {
    PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
    configurer.setLocation(new FileSystemResource(
        "C:\\Users\\m.peluchi.rocha\\Documents\\Projetos\\SpringBatchConfig.properties"));
    return configurer;
  }
}
