package com.mpr.cursobatch.jobs.bankcase.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mpr.cursobatch.domain.bank.BankAccount;

@Configuration
public class ClassifierAccountWriterConfig {

  @Bean
  public ClassifierCompositeItemWriter<BankAccount> classifierAccountWirter(
      @Qualifier("invalidClientWriter") FlatFileItemWriter<BankAccount> invalidClientWriter,
      CompositeItemWriter<BankAccount> validClienItemWriter) {
    return new ClassifierCompositeItemWriterBuilder<BankAccount>()
        .classifier(classifier(invalidClientWriter, validClienItemWriter))
        .build();
  }

  private Classifier<BankAccount, ItemWriter<? super BankAccount>> classifier(
      FlatFileItemWriter<BankAccount> invalidClientWriter,
      CompositeItemWriter<BankAccount> validClienItemWriter) {
    return new Classifier<BankAccount, ItemWriter<? super BankAccount>>() {
      @Override
      public ItemWriter<? super BankAccount> classify(BankAccount account) {
        if (account.getTipo() != null)
          return validClienItemWriter;
        else
          return invalidClientWriter;
      }
    };
  }

}
