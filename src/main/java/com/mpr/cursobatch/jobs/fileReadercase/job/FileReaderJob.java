package com.mpr.cursobatch.jobs.fileReadercase.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileReaderJob {

  // @Bean
  public Job printClients(JobRepository jobRepository, Step step) {
    return new JobBuilder("PrintClients", jobRepository)
        .start(step)
        .incrementer(new RunIdIncrementer())
        .build();
  }
}
