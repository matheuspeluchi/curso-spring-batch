package com.mpr.cursobatch.jobs.general.tasklets;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;


public class PrintNameTasklet {

  private PrintNameTasklet() {}

  @Bean
  public static Tasklet printHello() {
    return (StepContribution contribution, ChunkContext chunkContext) -> {
      System.out.println("Hello world");
      return RepeatStatus.FINISHED;
    };
  }

}
