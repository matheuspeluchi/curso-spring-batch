package com.mpr.cursobatch.jobs.bankcase.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.domain.bank.BankAccount;
import com.mpr.cursobatch.domain.bank.BankClient;

// @Configuration
public class ContaBancariaStepConfig {

  @Bean
  public Step contaBancariaStep(JobRepository repository,
      PlatformTransactionManager transactionManager,
      ItemReader<BankClient> jdbcClientBankItemReader,
      ItemProcessor<BankClient, BankAccount> classifierBankProcessor,
      ItemWriter<BankAccount> impressaoContaWriter) {
    return new StepBuilder("contaBancariaStep", repository)
        .<BankClient, BankAccount>chunk(100, transactionManager)
        .reader(jdbcClientBankItemReader)
        .processor(classifierBankProcessor)
        .writer(impressaoContaWriter)
        .build();
  }

}
