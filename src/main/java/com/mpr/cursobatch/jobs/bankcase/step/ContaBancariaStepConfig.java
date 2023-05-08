package com.mpr.cursobatch.jobs.bankcase.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.domain.bank.BankAccount;
import com.mpr.cursobatch.domain.bank.BankClient;

@Configuration
public class ContaBancariaStepConfig {

  @Bean
  public Step contaBancariaStep(JobRepository repository,
      PlatformTransactionManager transactionManager,
      JdbcPagingItemReader<BankClient> jdbcClientBankItemReader,
      ItemProcessor<BankClient, BankAccount> classifierBankProcessor,
      ClassifierCompositeItemWriter<BankAccount> classifierAccountWirter,
      @Qualifier("fileAccountWriter") FlatFileItemWriter<BankAccount> validClientWriter,
      @Qualifier("invalidClientWriter") FlatFileItemWriter<BankAccount> invalidClientWriter) {
    return new StepBuilder("contaBancariaStep", repository)
        .<BankClient, BankAccount>chunk(100, transactionManager)
        .reader(jdbcClientBankItemReader)
        .processor(classifierBankProcessor)
        .writer(classifierAccountWirter)
        .stream(validClientWriter)
        .stream(invalidClientWriter)
        .build();
  }

}
