package com.mpr.cursobatch.jobs.validationcase.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class DefaultWriterConfig {

  // @Bean
  public static ItemWriter fixedWidthFileWriter() {
    return items -> items.forEach(System.out::println);
  }

}
