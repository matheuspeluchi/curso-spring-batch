package com.mpr.cursobatch.jobs.jdbcpagingcase.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.domain.Client;
import com.mpr.cursobatch.jobs.general.wirters.DefaultWriterConfig;

@Configuration
public class JdbcPagingStepConfig {


  // @Bean
  @SuppressWarnings({"unchecked"})
  public Step jdbcPagingStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      JdbcPagingItemReader<Client> jdbcPagingItemReader) {

    return new StepBuilder("jdbcPagingStep", jobRepository)
        .chunk(1, transactionManager)
        .reader(jdbcPagingItemReader)
        .writer(DefaultWriterConfig.fixedWidthFileWriter())
        .build();
  }



}


