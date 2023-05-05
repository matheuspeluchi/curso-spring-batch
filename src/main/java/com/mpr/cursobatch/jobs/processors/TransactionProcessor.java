package com.mpr.cursobatch.jobs.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import com.mpr.cursobatch.domain.ClientsTransaction;

public class TransactionProcessor implements ItemProcessor<ClientsTransaction, ClientsTransaction> {

  @Override
  @Nullable
  public ClientsTransaction process(@NonNull ClientsTransaction transaction) throws Exception {
    System.out
        .println(
            String.format("\n Aplicando regras de negocio a transação %s", transaction.getId()));
    return transaction;
  }

}
