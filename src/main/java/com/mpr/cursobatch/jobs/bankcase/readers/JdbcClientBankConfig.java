package com.mpr.cursobatch.jobs.bankcase.readers;

import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import com.mpr.cursobatch.domain.bank.BankClient;

@Configuration
public class JdbcClientBankConfig {

  @Bean
  public JdbcPagingItemReader<BankClient> jdbcClientBankItemReader(
      @Qualifier("appDataSource") DataSource dataSource, PagingQueryProvider queryProvider) {
    return new JdbcPagingItemReaderBuilder<BankClient>()
        .name("jdbcClientBankItemReader")
        .dataSource(dataSource)
        .queryProvider(queryProvider)
        .rowMapper(new BeanPropertyRowMapper<BankClient>(BankClient.class))
        .build();
  }

  @Bean
  public SqlPagingQueryProviderFactoryBean queryProvider(
      @Qualifier("appDataSource") DataSource dataSource) {
    SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
    queryProvider.setDataSource(dataSource);
    queryProvider.setSelectClause("select *");
    queryProvider.setFromClause("from cliente");
    queryProvider.setSortKey("email");
    return queryProvider;
  }

}
