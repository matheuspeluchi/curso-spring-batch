package com.mpr.cursobatch.jobs.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import com.mpr.cursobatch.domain.Client;

public class ClientProcessor implements ItemProcessor<Client, Client> {

  @Override
  @Nullable
  public Client process(@NonNull Client client) throws Exception {
    System.out
        .println(String.format("\n Aplicando regras de negocio no cliente %s", client.getEmail()));
    return client;
  }

}
