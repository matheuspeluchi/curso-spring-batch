package com.mpr.cursobatch.jobs.processors;

import java.util.HashSet;
import java.util.Set;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mpr.cursobatch.domain.SingleClient;

@Configuration
public class ValidatorProcessorConfig {

  private Set<String> emails = new HashSet<>();

  // @Bean
  public ItemProcessor<SingleClient, SingleClient> validatorProcessor() throws Exception {

    return new CompositeItemProcessorBuilder<SingleClient, SingleClient>()
        .delegates(beanValidatingItemProcessor(), emailValidatingItemProcessor())
        .build();
  }

  private BeanValidatingItemProcessor<SingleClient> beanValidatingItemProcessor() throws Exception {
    BeanValidatingItemProcessor<SingleClient> processor = new BeanValidatingItemProcessor<>();
    processor.setFilter(true);
    processor.afterPropertiesSet();
    return processor;
  }

  private ValidatingItemProcessor<SingleClient> emailValidatingItemProcessor() throws Exception {
    ValidatingItemProcessor<SingleClient> processor = new ValidatingItemProcessor<>();
    processor.setValidator(validator());
    processor.setFilter(true);
    processor.afterPropertiesSet();
    return processor;
  }

  private Validator<SingleClient> validator() {
    return new Validator<SingleClient>() {

      @Override
      public void validate(SingleClient client) throws ValidationException {
        if (emails.contains(client.getEmail()))
          throw new ValidationException(
              String.format("O cliente %s já foi processado!", client.getEmail()));
        emails.add(client.getEmail());
      }

    };
  }
}
