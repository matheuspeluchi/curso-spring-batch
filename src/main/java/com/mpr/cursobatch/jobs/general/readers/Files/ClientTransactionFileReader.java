package com.mpr.cursobatch.jobs.general.readers.Files;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import com.mpr.cursobatch.domain.Client;
import com.mpr.cursobatch.domain.ClientsTransaction;

public class ClientTransactionFileReader implements ItemStreamReader<Client>,
    ResourceAwareItemReaderItemStream<Client> {


  private Object currentObject;
  private FlatFileItemReader<Object> delegate;

  public ClientTransactionFileReader(FlatFileItemReader<Object> delegate) {
    this.delegate = delegate;
  }

  @Override
  public void open(ExecutionContext executionContext) throws ItemStreamException {
    delegate.open(executionContext);
  }

  @Override
  public void update(ExecutionContext executionContext) throws ItemStreamException {
    delegate.update(executionContext);
  }

  @Override
  public void close() throws ItemStreamException {
    delegate.close();
  }

  @Override
  @Nullable
  public Client read() throws Exception {
    if (currentObject == null) {
      currentObject = delegate.read();
    }

    Client client = (Client) currentObject;
    currentObject = null;

    if (client != null) {
      while (peek() instanceof ClientsTransaction)
        client.getTransactions().add((ClientsTransaction) currentObject);
    }
    return client;
  }

  private Object peek() throws Exception {
    currentObject = delegate.read();
    return currentObject;
  }

  @Override
  public void setResource(Resource resource) {
    delegate.setResource(resource);
  }

}
