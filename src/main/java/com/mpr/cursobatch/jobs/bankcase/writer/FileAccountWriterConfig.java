package com.mpr.cursobatch.jobs.bankcase.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import com.mpr.cursobatch.domain.bank.BankAccount;

@Configuration
public class FileAccountWriterConfig {
  @Bean
  public FlatFileItemWriter<BankAccount> fileAccountWriter() {
    return new FlatFileItemWriterBuilder()
        .name("fileAccountWriter")
        .resource(new FileSystemResource("./assets/bankAccount.txt"))
        .delimited()
        .names("tipo", "limite", "cliente_id")
        .build();
  }

}
