package com.mpr.cursobatch.jobs.bankcase.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mpr.cursobatch.domain.bank.BankAccount;
import com.mpr.cursobatch.domain.bank.BankClient;

@Configuration
public class BankProcessorConfig {

  @Bean
  public ItemProcessor<BankClient, BankAccount> classifierBankProcessor() {
    return new ClassifierCompositeItemProcessorBuilder<BankClient, BankAccount>()
        .classifier(new BankAccountGeneratorClassifier())
        .build();
  }
}
