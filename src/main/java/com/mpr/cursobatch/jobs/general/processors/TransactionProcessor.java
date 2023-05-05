package com.mpr.cursobatch.jobs.general.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import com.mpr.cursobatch.domain.Transaction;

public class TransactionProcessor implements ItemProcessor<Transaction, Transaction> {

  @Override
  @Nullable
  public Transaction process(@NonNull Transaction transaction) throws Exception {
    System.out
        .println(
            String.format("\n Aplicando regras de negocio a transação %s", transaction.getId()));
    return transaction;
  }

}
