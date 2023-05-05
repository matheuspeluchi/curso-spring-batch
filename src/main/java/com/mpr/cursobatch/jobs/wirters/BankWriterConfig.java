package com.mpr.cursobatch.jobs.wirters;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mpr.cursobatch.domain.BankAccount;

@Configuration
public class BankWriterConfig {
  @Bean
  public ItemWriter<BankAccount> impressaoContaWriter() {
    return contas -> contas.forEach(System.out::println);
  }
}
