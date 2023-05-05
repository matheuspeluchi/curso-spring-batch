package com.mpr.cursobatch.jobs.processors.bankProcessors;

import org.springframework.batch.item.ItemProcessor;
import com.mpr.cursobatch.domain.BankAccount;
import com.mpr.cursobatch.domain.BankClient;
import com.mpr.cursobatch.domain.Tipo;

public class ContaPrataItemProcessor implements ItemProcessor<BankClient, BankAccount> {

  @Override
  public BankAccount process(BankClient client) throws Exception {
    BankAccount account = new BankAccount();
    account.setCliente_id(client.getEmail());
    account.setTipo(Tipo.PRATA);
    account.setLimite(500.0);
    return account;
  }
}
