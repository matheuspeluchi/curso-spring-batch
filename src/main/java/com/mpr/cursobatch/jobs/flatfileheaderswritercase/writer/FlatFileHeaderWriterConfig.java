package com.mpr.cursobatch.jobs.flatfileheaderswritercase.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.WritableResource;
import com.mpr.cursobatch.domain.Entry;
import com.mpr.cursobatch.domain.EntryGroup;

@Configuration
public class FlatFileHeaderWriterConfig {
  @Bean
  @StepScope
  public FlatFileItemWriter<EntryGroup> bugetStatementWriter(
      @Value("#{jobParameters['budgetStatement']}") WritableResource budgetStatement,
      BudgetStatmentFooter budgetStatmentFooter) {

    return new FlatFileItemWriterBuilder<EntryGroup>()
        .name("bugetStatementWriter")
        .resource(budgetStatement)
        .lineAggregator(lineAggregator())
        .headerCallback(headerCallback())
        .footerCallback(budgetStatmentFooter)
        .build();
  }


  private FlatFileHeaderCallback headerCallback() {
    return new FlatFileHeaderCallback() {

      @Override
      public void writeHeader(Writer writer) throws IOException {
        writer.append("\n");
        writer.append(String.format("SISTEMA INTEGRADO: XPTO \t\t\t\t DATA: %s\n",
            new SimpleDateFormat("dd/MM/yyyy").format(new Date())));
        writer.append(String.format("MÓDULO: ORÇAMENTO \t\t\t\t\t\t\t HORA: %s\n",
            new SimpleDateFormat("HH:MM").format(new Date())));
        writer.append(String.format("\t\t\tDEMONSTRATIVO ORCAMENTARIO\n"));
        writer.append(String.format(
            "----------------------------------------------------------------------------\n"));
        writer.append(String.format("CODIGO NOME VALOR\n"));
        writer.append(String.format("\t Data Descricao Valor \n"));
        writer.append(String.format(
            "----------------------------------------------------------------------------\n"));
      }

    };
  }



  private LineAggregator<EntryGroup> lineAggregator() {
    return new LineAggregator<EntryGroup>() {

      @Override
      public String aggregate(EntryGroup entryGroup) {
        String formattedGroupEntry =
            String.format("[%d] %s - %s\n", entryGroup.getCodigoNaturezaDespesa(),
                entryGroup.getDescricaoNaturezaDespesa(),
                NumberFormat.getCurrencyInstance().format(entryGroup.getTotal()));

        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : entryGroup.getLancamentos()) {
          stringBuilder.append(String.format("\t [%s] %s - %s\n",
              new SimpleDateFormat("dd/MM/yyyy").format(entry.getData()),
              entry.getDescricao(),
              NumberFormat.getCurrencyInstance().format(entry.getValor())));
        }
        String formattedEntry = stringBuilder.toString();
        return formattedGroupEntry + formattedEntry;
      }

    };
  }

}
