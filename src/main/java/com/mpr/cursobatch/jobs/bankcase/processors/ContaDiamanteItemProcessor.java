package com.mpr.cursobatch.jobs.bankcase.processors;

import org.springframework.batch.item.ItemProcessor;
import com.mpr.cursobatch.domain.BankAccount;
import com.mpr.cursobatch.domain.BankClient;
import com.mpr.cursobatch.domain.Tipo;

public class ContaDiamanteItemProcessor implements ItemProcessor<BankClient, BankAccount> {

  @Override
  public BankAccount process(BankClient client) throws Exception {
    BankAccount account = new BankAccount();
    account.setCliente_id(client.getEmail());
    account.setTipo(Tipo.DIAMANTE);
    account.setLimite(5000.0);
    return account;
  }


}
