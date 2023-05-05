package com.mpr.cursobatch.jobs.general.readers.Files;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import com.mpr.cursobatch.domain.Client;

@Configuration
public class DelimiterFileReaderConfig {

  // @Bean
  @StepScope
  public FlatFileItemReader<Client> delimiterFileReader(
      @Value("#{jobParameters['clientFile']}") Resource clientFile) {

    return new FlatFileItemReaderBuilder<Client>().name("delimiterFileReader")
        .resource(clientFile)
        .delimited()
        .names("name", "lastName", "age", "email").targetType(Client.class)
        .build();
  }
}
