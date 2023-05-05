package com.mpr.cursobatch.jobs.general.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import com.mpr.cursobatch.domain.client.ClientWithTransaction;

public class ClientProcessor
    implements ItemProcessor<ClientWithTransaction, ClientWithTransaction> {

  @Override
  @Nullable
  public ClientWithTransaction process(@NonNull ClientWithTransaction client) throws Exception {
    System.out
        .println(String.format("\n Aplicando regras de negocio no cliente %s", client.getEmail()));
    return client;
  }

}
