package com.mpr.cursobatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

  @Bean
  public Job printMessage(JobRepository jobRepository, Step step) {
    return new JobBuilder("print-message", jobRepository).start(step).build();
  }


  @Bean
  public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("step1", jobRepository)
        .tasklet((StepContribution contribution, ChunkContext chunkContext) -> {
          this.printHello();
          return RepeatStatus.FINISHED;
        }, transactionManager).build();
  }


  private void printHello() {
    System.out.println("#############################");
    System.out.println("#############################");
    System.out.println("Esta é a mensagem");
    System.out.println("#############################");
    System.out.println("#############################");
  }
}
