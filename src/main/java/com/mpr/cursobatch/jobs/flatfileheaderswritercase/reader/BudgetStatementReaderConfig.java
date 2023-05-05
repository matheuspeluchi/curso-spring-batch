package com.mpr.cursobatch.jobs.flatfileheaderswritercase.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import com.mpr.cursobatch.domain.EntryGroup;

@Configuration
public class BudgetStatementReaderConfig {
	@StepScope
	@Bean
	public MultiResourceItemReader<EntryGroup> demonstrativoOrcamentarioReader(
			@Value("#{jobParameters['arquivosLancamento']}") Resource[] arquivosLancamento,
			EntryGroupReader grupoLancamentoReader) {
		return new MultiResourceItemReaderBuilder<EntryGroup>()
				.name("demonstrativoOrcamentarioReader")
				.resources(arquivosLancamento)
				.delegate(grupoLancamentoReader)
				.build();
	}
}
