package com.mpr.cursobatch.jobs.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.domain.Client;
import com.mpr.cursobatch.jobs.wirters.DefaultWriterConfig;

@Configuration
public class FileReaderStepConfig {


  // @Bean
  @SuppressWarnings({"unchecked"})
  public Step fixedWidthFileReaderStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      MultiResourceItemReader<Client> multiplesFilesClientsTransactionsReader) {

    return new StepBuilder("FileReaderConfigStep", jobRepository)
        .chunk(1, transactionManager)
        .reader(multiplesFilesClientsTransactionsReader)
        .writer(DefaultWriterConfig.fixedWidthFileWriter())
        .build();
  }



}


