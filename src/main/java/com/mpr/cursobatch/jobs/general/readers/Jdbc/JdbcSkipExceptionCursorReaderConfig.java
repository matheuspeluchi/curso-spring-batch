package com.mpr.cursobatch.jobs.general.readers.Jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import com.mpr.cursobatch.domain.Client;

@Configuration
public class JdbcSkipExceptionCursorReaderConfig {

  // @Bean
  public ItemReader<Client> jdbcSkipExceptionCursorItemReader(
      @Qualifier("appDataSource") DataSource dataSource) {
    return new JdbcCursorItemReaderBuilder<Client>()
        .name("jdbcSkipExceptionCursorItemReader")
        .dataSource(dataSource)
        .sql("select * from customer")
        .rowMapper(rowMapper())
        .build();
  }

  private RowMapper<Client> rowMapper() {
    return new RowMapper<Client>() {
      @Override
      public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        if (rs.getRow() <= 11)
          throw new SQLException(
              String.format("Encerrando a execução - Cliente Inválido %s", rs.getString("email")));
        else
          return clientRowMapper(rs);
      }
    };
  }

  protected Client clientRowMapper(ResultSet rs) throws SQLException {
    Client client = new Client();
    client.setName(rs.getString("name"));
    client.setLastName(rs.getString("lastName"));
    client.setAge(rs.getString("age"));
    client.setEmail(rs.getString("email"));
    return client;

  }

}
