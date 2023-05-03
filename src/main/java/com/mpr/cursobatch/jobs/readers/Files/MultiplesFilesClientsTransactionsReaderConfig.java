package com.mpr.cursobatch.jobs.readers.Files;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MultiplesFilesClientsTransactionsReaderConfig {

  @SuppressWarnings({"rawTypes", "unchecked"})
  @Bean
  @StepScope
  public MultiResourceItemReader multiplesFilesClientsTransactionsReader(
      @Value("#{jobParameters['clientFiles']}") Resource[] clientFiles,
      FlatFileItemReader multiplesFormatsFileReader) {

    return new MultiResourceItemReaderBuilder()
        .name("multiplesFilesClientsTransactionsReader")
        .resources(clientFiles)
        .delegate(new ClientTransactionFileReader(multiplesFormatsFileReader))
        .build();

  }
}
