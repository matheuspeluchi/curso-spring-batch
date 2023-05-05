package com.mpr.cursobatch.jobs.classifiercase.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.domain.client.SingleClient;
import com.mpr.cursobatch.jobs.general.wirters.DefaultWriterConfig;

@Configuration
public class ClassifierStepConfig {


  // @Bean
  @SuppressWarnings({"unchecked"})
  public Step scriptStep(JobRepository jobRepository,
      PlatformTransactionManager transactionManager,
      FlatFileItemReader multiplesFormatsFileReader,
      ItemProcessor classifierProcessor) {

    return new StepBuilder("scriptStep", jobRepository)
        .<SingleClient, SingleClient>chunk(1, transactionManager)
        .reader(multiplesFormatsFileReader)
        .processor(classifierProcessor)
        .writer(DefaultWriterConfig.fixedWidthFileWriter())
        .build();
  }



}


