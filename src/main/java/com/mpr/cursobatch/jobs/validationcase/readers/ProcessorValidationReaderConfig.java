package com.mpr.cursobatch.jobs.validationcase.readers;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import com.mpr.cursobatch.domain.client.SingleClient;

// @Configuration
public class ProcessorValidationReaderConfig {

  @StepScope
  @Bean
  public FlatFileItemReader<SingleClient> processorValidationReader(
      @Value("#{jobParameters['clientFiles']}") Resource clientsFile) {
    return new FlatFileItemReaderBuilder<SingleClient>()
        .name("processorValidationReader")
        .resource(clientsFile)
        .delimited()
        .names("name", "age", "email")
        .targetType(
            SingleClient.class)
        .build();
  }

}
