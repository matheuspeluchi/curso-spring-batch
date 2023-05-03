package com.mpr.cursobatch.jobs.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.domain.Client;
import com.mpr.cursobatch.jobs.wirters.DefaultWriterConfig;

@Configuration
public class JdbcCursorStepConfig {


  // @Bean
  @SuppressWarnings({"unchecked"})
  public Step jdbcReaderStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      JdbcCursorItemReader<Client> jdbcCursorItemReader) {

    return new StepBuilder("jdbcReaderStep", jobRepository)
        .chunk(1, transactionManager)
        .reader(jdbcCursorItemReader)
        .writer(DefaultWriterConfig.fixedWidthFileWriter())
        .build();
  }



}


