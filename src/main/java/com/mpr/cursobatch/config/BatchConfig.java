package com.mpr.cursobatch.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {

  @Bean
  public Job printMessage(JobRepository jobRepository, Step step) {
    return new JobBuilder("print-message", jobRepository).start(step)
        .incrementer(new RunIdIncrementer()).build();
  }


  // @Bean
  // public Step printNameStep(JobRepository jobRepository, PlatformTransactionManager
  // transactionManager) {
  // return new StepBuilder("Setep-PrintName", jobRepository)
  // .tasklet(printHello(null), transactionManager).build();
  // }


  @Bean
  public Step printEvenOrOddStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager) {
    return new StepBuilder("Setep-PrintEvenOrOdd", jobRepository)
        .<Integer, String>chunk(0, transactionManager).reader(countReader())
        .processor(numberProcessor()).writer(printWriter()).build();

  }


  @Bean
  @StepScope
  public Tasklet printHello(@Value("#{jobParameters['nome']}") String name) {
    return (StepContribution contribution, ChunkContext chunkContext) -> {
      System.out.println(String.format("Olá, %s!", name));
      return RepeatStatus.FINISHED;
    };
  }


  private IteratorItemReader<Integer> countReader() {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    return new IteratorItemReader<>(numbers.iterator());
  }

  private FunctionItemProcessor<Integer, String> numberProcessor() {
    return new FunctionItemProcessor<>((item -> item % 2 == 0 ? String.format("Item %s é Par", item)
        : String.format("Item %s é Ímpar", item)));
  }

  private ItemWriter<String> printWriter() {
    return itens -> itens.forEach((System.out::println));
  }
}


