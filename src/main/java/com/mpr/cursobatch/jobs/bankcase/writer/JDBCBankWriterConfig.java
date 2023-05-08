package com.mpr.cursobatch.jobs.bankcase.writer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mpr.cursobatch.domain.bank.BankAccount;

@Configuration
public class JDBCBankWriterConfig {
  @Bean
  public JdbcBatchItemWriter<BankAccount> jdbcAccountWriter(
      @Qualifier("appDataSource") DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<BankAccount>()
        .dataSource(dataSource)
        .sql("INSERT INTO conta (tipo, limite, cliente_id) values (?, ?, ?)")
        .itemPreparedStatementSetter(itemPreparedStatementSetter())
        .build();
  }

  private ItemPreparedStatementSetter<BankAccount> itemPreparedStatementSetter() {
    return new ItemPreparedStatementSetter<BankAccount>() {

      @Override
      public void setValues(BankAccount bankAccount, PreparedStatement ps) throws SQLException {
        ps.setString(1, bankAccount.getTipo().name());
        ps.setDouble(2, bankAccount.getLimite());
        ps.setString(3, bankAccount.getCliente_id());
      }

    };
  }
}
