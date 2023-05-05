package com.mpr.cursobatch.jobs.bankcase.processors;

import org.springframework.batch.item.ItemProcessor;
import com.mpr.cursobatch.domain.bank.BankAccount;
import com.mpr.cursobatch.domain.bank.BankClient;
import com.mpr.cursobatch.domain.bank.Tipo;

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
