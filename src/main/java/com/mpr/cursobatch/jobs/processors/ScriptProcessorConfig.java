package com.mpr.cursobatch.jobs.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ScriptItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.mpr.cursobatch.domain.SingleClient;

@Configuration
public class ScriptProcessorConfig {


  @Bean
  public ItemProcessor<SingleClient, SingleClient> scriptProcessor() {
    return new ScriptItemProcessorBuilder<SingleClient, SingleClient>()
        .language("nashorn")
        .scriptSource(
            "var email = item.getEmail();"
                + "var fileExist = `ls | grep ${email}.txt`;"
                + "if (!fileExist) item; else null;")
        .build();
  }


}
