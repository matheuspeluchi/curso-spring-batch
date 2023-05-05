package com.mpr.cursobatch.jobs.validationcase.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.domain.SingleClient;
import com.mpr.cursobatch.jobs.general.wirters.DefaultWriterConfig;

@Configuration
public class ValidatorStepConfig {


  // @Bean
  @SuppressWarnings({"unchecked"})
  public Step validatorStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      FlatFileItemReader<SingleClient> processadorValidacaoReader,
      ItemProcessor<SingleClient, SingleClient> validationProcessor) {

    return new StepBuilder("validatorStep", jobRepository)
        .<SingleClient, SingleClient>chunk(1, transactionManager)
        .reader(processadorValidacaoReader)
        .processor(validationProcessor)
        .writer(DefaultWriterConfig.fixedWidthFileWriter())
        .build();
  }



}


