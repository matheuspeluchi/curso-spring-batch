package com.mpr.cursobatch.jobs.filereadercase.readers;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import com.mpr.cursobatch.domain.Transaction;

// @Configuration
public class FixedWidthFileReaderConfig {

  // @Bean
  // @StepScope
  public FlatFileItemReader<Transaction> fixedWidthFileReader(
      @Value("#{jobParameters['clientFile']}") Resource clientFile) {

    return new FlatFileItemReaderBuilder<Transaction>().name("fixedWidthFileReader")
        .resource(clientFile)
        .fixedLength()
        .columns(new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43))
        .names("name", "lastName", "age", "email").targetType(Transaction.class).build();
  }
}
