package com.mpr.cursobatch.jobs.flatfilewritercase.writer;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.WritableResource;
import com.mpr.cursobatch.domain.Client;

// @Configuration
public class FixedWidthWriterConfig {
  @Bean
  @StepScope
  public FlatFileItemWriter<Client> flatFileFixedWidthWriter(
      @Value("#{jobParameters['clientOutfile']}") WritableResource clientOutfile) {
    return new FlatFileItemWriterBuilder<Client>()
        .name("flatFileFixedWidthWriter")
        .resource(clientOutfile)
        .formatted()
        .format("%-9s %-9s %-2s %-19s")
        .names("name", "lastName", "age", "email")
        .build();
  }

}
