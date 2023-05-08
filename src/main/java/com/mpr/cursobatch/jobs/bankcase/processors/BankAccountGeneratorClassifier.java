package com.mpr.cursobatch.jobs.bankcase.processors;

import java.util.HashMap;
import java.util.Map;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;
import com.mpr.cursobatch.domain.bank.BankAccount;
import com.mpr.cursobatch.domain.bank.BankClient;
import com.mpr.cursobatch.domain.bank.Tipo;

public class BankAccountGeneratorClassifier
    implements Classifier<BankClient, ItemProcessor<?, ? extends BankAccount>> {

  private static final Map<Tipo, ItemProcessor<BankClient, BankAccount>> processors =
      new HashMap<Tipo, ItemProcessor<BankClient, BankAccount>>() {
        {
          put(Tipo.PRATA, new ContaPrataItemProcessor());
          put(Tipo.OURO, new ContaOuroItemProcessor());
          put(Tipo.PLATINA, new ContaPlatinaItemProcessor());
          put(Tipo.DIAMANTE, new ContaDiamanteItemProcessor());
          put(Tipo.INVALIDA, new ContaInvalidaItemProcessor());
        }
      };

  @Override
  public ItemProcessor<BankClient, BankAccount> classify(BankClient client) {
    Tipo tipo = Tipo.fromFaixaSalarial(client.getFaixa_salarial());
    return processors.get(tipo);
  }

}
