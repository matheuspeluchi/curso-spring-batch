package com.mpr.cursobatch.jobs.general.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.jobs.general.tasklets.PrintNameTasklet;

@Configuration
public class PrintNameStepConfig {

  // INACTIVE
  // @Bean
  public Step printNameStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager) {
    return new StepBuilder("Step-PrintName", jobRepository)
        .tasklet(PrintNameTasklet.printHello(), transactionManager).build();
  }



}


