package com.mpr.cursobatch.jobs.bankcase.writer;

import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mpr.cursobatch.domain.bank.BankAccount;
import com.mpr.cursobatch.domain.bank.BankClient;

@Configuration
public class MultipleWriterConfig {

  @Bean
  public CompositeItemWriter<BankAccount> compositeBankItemWriter(
      @Qualifier("fileAccountWriter") FlatFileItemWriter<BankAccount> fileAccountWriter,
      JdbcBatchItemWriter<BankAccount> jdbcAccountWriter) {
    return new CompositeItemWriterBuilder<BankAccount>().delegates(
        fileAccountWriter, jdbcAccountWriter)
        .build();
  };



}
