package com.mpr.cursobatch.jobs.jdbccursorcase.readers;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.mpr.cursobatch.domain.Client;

@Configuration
public class JdbcCursorReaderConfig {

  // @Bean
  public JdbcCursorItemReader<Client> jdbcCursorItemReader(
      @Qualifier("appDataSource") DataSource dataSource) {
    return new JdbcCursorItemReaderBuilder<Client>()
        .name("jdbcCursorItemReader")
        .dataSource(dataSource)
        .sql("select * from customer")
        .rowMapper(new BeanPropertyRowMapper<Client>(Client.class))
        .build();
  }

}
