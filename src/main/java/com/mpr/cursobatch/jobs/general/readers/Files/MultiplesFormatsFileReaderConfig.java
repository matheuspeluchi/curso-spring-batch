package com.mpr.cursobatch.jobs.general.readers.Files;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import com.mpr.cursobatch.domain.Client;

@Configuration
public class MultiplesFormatsFileReaderConfig {

  @Bean
  @StepScope
  public FlatFileItemReader multiplesFormatsFileReader(
      @Value("#{jobParameters['clientFile']}") Resource clientFile,
      LineMapper lineMapper) {

    return new FlatFileItemReaderBuilder<Client>()
        .name("multiplesFormatsFileReader")
        .resource(clientFile)
        .lineMapper(lineMapper)
        .build();
  }
}
