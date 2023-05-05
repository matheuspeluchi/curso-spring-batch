package com.mpr.cursobatch.jobs.flatfilewritercase.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.domain.Client;

// @Configuration
public class FixedWidhWriterStepConfig {
  @Bean
  public Step fixedWidhWriterStep(JobRepository repository,
      PlatformTransactionManager transactionManager,
      ItemReader<Client> flatFileFixedWidthReader,
      ItemWriter<Client> flatFileFixedWidthWriter) {
    return new StepBuilder("FileReaderConfigStep", repository)
        .<Client, Client>chunk(1, transactionManager)
        .reader(flatFileFixedWidthReader)
        .writer(flatFileFixedWidthWriter)
        .build();
  }
}
