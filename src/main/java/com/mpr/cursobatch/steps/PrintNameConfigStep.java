package com.mpr.cursobatch.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.tasklets.PrintNameTasklet;

@Configuration
public class PrintNameConfigStep {
  @Bean
  public Step printNameStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager) {
    return new StepBuilder("Step-PrintName", jobRepository)
        .tasklet(PrintNameTasklet.printHello(), transactionManager).build();
  }



}


