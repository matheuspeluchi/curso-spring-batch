package com.mpr.cursobatch.jobs.general.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrintNameJob {

  // INACTIVE
  // @Bean
  public Job printMessage(JobRepository jobRepository, Step step) {
    return new JobBuilder("print-message", jobRepository).start(step)
        .incrementer(new RunIdIncrementer()).build();
  }


}
