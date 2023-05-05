package com.mpr.cursobatch.jobs.flatfileheaderswritercase.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import com.mpr.cursobatch.domain.EntryGroup;

@Component
public class EntryGroupReader implements ItemStreamReader<EntryGroup>,
		ResourceAwareItemReaderItemStream<EntryGroup> {
	@Autowired
	// Esse aqui lê do arquivo
	// private FlatFileItemReader<GrupoLancamento> delegate;
	// Esse aqui lê do banco
	private JdbcCursorItemReader<EntryGroup> delegate;
	private EntryGroup lancamentoAtual;

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
	public void setResource(Resource resource) {
		// Descomentar para a leitura de arquivo
		// delegate.setResource(resource);
	}

	@Override
	public EntryGroup read() throws Exception {
		if (lancamentoAtual == null)
			lancamentoAtual = delegate.read();

		EntryGroup grupoLancamento = lancamentoAtual;
		lancamentoAtual = null;

		if (grupoLancamento != null) {
			EntryGroup proxLancamento = peek();
			while (isLancamentoRelacionado(grupoLancamento, proxLancamento)) {
				grupoLancamento.getLancamentos().add(lancamentoAtual.getLancamentoTmp());
				proxLancamento = peek();
			}
			grupoLancamento.getLancamentos().add(grupoLancamento.getLancamentoTmp());
		}
		return grupoLancamento;
	}

	private boolean isLancamentoRelacionado(EntryGroup grupoLancamento,
			EntryGroup proxLancamento) {
		return proxLancamento != null && proxLancamento.getCodigoNaturezaDespesa()
				.equals(grupoLancamento.getCodigoNaturezaDespesa());
	}

	public EntryGroup peek() throws Exception {
		lancamentoAtual = delegate.read();
		return lancamentoAtual;
	}
}
