package com.mpr.cursobatch.jobs.flatfileheaderswritercase.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.BindException;
import com.mpr.cursobatch.domain.EntryGroup;
import com.mpr.cursobatch.domain.Entry;

@Configuration
public class EntryFileReaderConfig {

	@Bean
	public FlatFileItemReader<EntryGroup> entryFileReaderConfigReader() {
		return new FlatFileItemReaderBuilder<EntryGroup>()
				.name("entryFileReaderConfigReader")
				.delimited()
				.names("codigoNaturezaDespesa",
						"descricaoNaturezaDespesa",
						"descricaoLancamento",
						"dataLancamento",
						"valorLancamento")
				.fieldSetMapper(grupoLancamentoFieldSetMapper())
				.build();
	}

	private FieldSetMapper<EntryGroup> grupoLancamentoFieldSetMapper() {
		return new FieldSetMapper<EntryGroup>() {

			@Override
			public EntryGroup mapFieldSet(FieldSet fieldSet) throws BindException {
				EntryGroup grupo = new EntryGroup();
				grupo.setCodigoNaturezaDespesa(fieldSet.readInt("codigoNaturezaDespesa"));
				grupo.setDescricaoNaturezaDespesa(fieldSet.readString("descricaoNaturezaDespesa"));
				grupo.setLancamentoTmp(new Entry());
				grupo.getLancamentoTmp().setData(fieldSet.readDate("dataLancamento"));
				grupo.getLancamentoTmp().setDescricao(fieldSet.readString("descricaoLancamento"));
				grupo.getLancamentoTmp().setValor(fieldSet.readDouble("valorLancamento"));
				return grupo;
			}

		};
	}
}
