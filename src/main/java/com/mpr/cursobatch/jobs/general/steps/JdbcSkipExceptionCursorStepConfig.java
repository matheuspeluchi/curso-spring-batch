package com.mpr.cursobatch.jobs.general.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.domain.client.ClientWithTransaction;
import com.mpr.cursobatch.jobs.general.wirters.DefaultWriterConfig;

@Configuration
public class JdbcSkipExceptionCursorStepConfig {


  // @Bean
  @SuppressWarnings({"unchecked"})
  public Step jdbcReaderStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      ItemReader<ClientWithTransaction> jdbcSkipExceptionCursorItemReader) {

    return new StepBuilder("jdbcReaderStep", jobRepository)
        .chunk(1, transactionManager)
        .reader(jdbcSkipExceptionCursorItemReader)
        .writer(DefaultWriterConfig.fixedWidthFileWriter())
        .faultTolerant()
        .skip(Exception.class)
        .skipLimit(2)
        .build();
  }



}


