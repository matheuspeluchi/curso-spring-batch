package com.mpr.cursobatch.jobs.readers.Files;

import java.util.HashMap;
import java.util.Map;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mpr.cursobatch.domain.Client;
import com.mpr.cursobatch.domain.ClientsTransaction;

@Configuration
public class ClientTransactionLineMapperConfig {

  @Bean
  @SuppressWarnings({"rawtypes", "unchecked"})
  public PatternMatchingCompositeLineMapper lineMapper() {
    PatternMatchingCompositeLineMapper lineMapper = new PatternMatchingCompositeLineMapper<>();
    lineMapper.setTokenizers(tokenizers());
    lineMapper.setFieldSetMappers(fieldSetMappers());
    return lineMapper;
  }

  @SuppressWarnings({"rawtypes"})
  private Map<String, FieldSetMapper> fieldSetMappers() {
    Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>();
    fieldSetMappers.put("0*", fieldSetMapper(Client.class));
    fieldSetMappers.put("1*", fieldSetMapper(ClientsTransaction.class));
    return fieldSetMappers;
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  private FieldSetMapper fieldSetMapper(Class targetClass) {
    BeanWrapperFieldSetMapper fieldSetMapper = new BeanWrapperFieldSetMapper<>();
    fieldSetMapper.setTargetType(targetClass);
    return fieldSetMapper;
  }

  private Map<String, LineTokenizer> tokenizers() {
    Map<String, LineTokenizer> tokenizers = new HashMap<>();
    tokenizers.put("0*", clientLineTokenizer());
    tokenizers.put("1*", transactionLineTokenizer());
    return tokenizers;
  }

  private LineTokenizer clientLineTokenizer() {
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setNames("name", "lastName", "age", "email");
    lineTokenizer.setIncludedFields(1, 2, 3, 4);
    return lineTokenizer;
  }

  private LineTokenizer transactionLineTokenizer() {
    DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setNames("id", "description", "value");
    lineTokenizer.setIncludedFields(1, 2, 3);
    return lineTokenizer;
  }



}
