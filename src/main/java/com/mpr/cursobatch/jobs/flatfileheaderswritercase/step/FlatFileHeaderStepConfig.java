package com.mpr.cursobatch.jobs.flatfileheaderswritercase.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import com.mpr.cursobatch.domain.EntryGroup;
import com.mpr.cursobatch.jobs.flatfileheaderswritercase.reader.EntryGroupReader;

@Configuration
public class FlatFileHeaderStepConfig {
  @Bean
  public Step fixedWidhWriterStep(JobRepository repository,
      PlatformTransactionManager transactionManager,
      // Esse aqui lê dos arquivos
      // MultiResourceItemReader<EntryGroupReader> budgetStatementReader,
      // Esse aqui lê do banco de dados
      EntryGroupReader budgetStatementReader,
      ItemWriter<EntryGroup> demonstrativoOrcamentarioWriter) {
    return new StepBuilder("FileReaderConfigStep", repository)
        .<EntryGroup, EntryGroup>chunk(1, transactionManager)
        .reader(budgetStatementReader)
        .writer(demonstrativoOrcamentarioWriter)
        .build();
  }
}
