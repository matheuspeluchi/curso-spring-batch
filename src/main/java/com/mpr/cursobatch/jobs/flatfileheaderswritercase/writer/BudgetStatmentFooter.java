package com.mpr.cursobatch.jobs.flatfileheaderswritercase.writer;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.List;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;
import com.mpr.cursobatch.domain.EntryGroup;

@Component
public class BudgetStatmentFooter implements FlatFileFooterCallback {

  private Double total = 0.0;

  @Override
  public void writeFooter(Writer writer) throws IOException {
    writer.append("\n");
    writer.append(String.format("\t\t\t\t\t\t\t\t Total: %s%n",
        NumberFormat.getCurrencyInstance().format(total)));
    writer
        .append(String.format("\t\t\t\t\t\t\t\t Código de Autenticação: %s%n", "sakjdlaskjdkjkg"));

  }

  @BeforeWrite
  public void beforeWrite(Chunk<EntryGroup> groups) {
    for (EntryGroup entryGroup : groups) {
      total += entryGroup.getTotal();
    }
  };

}
