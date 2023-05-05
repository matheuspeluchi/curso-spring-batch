package com.mpr.cursobatch.jobs.filereadercase.readers;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import com.mpr.cursobatch.domain.Transaction;

// @Configuration
public class DelimiterFileReaderConfig {

  // @Bean
  // @StepScope
  public FlatFileItemReader<Transaction> delimiterFileReader(
      @Value("#{jobParameters['clientFile']}") Resource clientFile) {

    return new FlatFileItemReaderBuilder<Transaction>().name("delimiterFileReader")
        .resource(clientFile)
        .delimited()
        .names("name", "lastName", "age", "email").targetType(Transaction.class)
        .build();
  }
}
