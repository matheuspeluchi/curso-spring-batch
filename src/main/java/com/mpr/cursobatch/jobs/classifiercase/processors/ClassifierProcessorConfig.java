package com.mpr.cursobatch.jobs.classifiercase.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mpr.cursobatch.domain.Client;
import com.mpr.cursobatch.jobs.general.processors.ClientProcessor;
import com.mpr.cursobatch.jobs.general.processors.TransactionProcessor;

@Configuration
public class ClassifierProcessorConfig {

  @Bean
  public ItemProcessor classifierProcessor() {
    return new ClassifierCompositeItemProcessorBuilder<>()
        .classifier(classifier())
        .build();
  }

  private Classifier classifier() {
    return new Classifier<Object, ItemProcessor>() {

      @Override
      public ItemProcessor classify(Object classifiable) {
        if (classifiable instanceof Client)
          return new ClientProcessor();
        else
          return new TransactionProcessor();
      }

    };
  }


}
