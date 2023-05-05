package com.mpr.cursobatch.jobs.flatfileheaderswritercase.reader;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
import com.mpr.cursobatch.domain.EntryGroup;
import com.mpr.cursobatch.domain.Entry;

@Configuration
public class EntryBank {
	@Bean
	public JdbcCursorItemReader<EntryGroup> entryBankReader(
			@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<EntryGroup>()
				.name("entryBankReader")
				.dataSource(dataSource)
				.sql("select * from lancamento")
				.rowMapper(rowMapper())
				.build();
	}

	private RowMapper<EntryGroup> rowMapper() {
		return new RowMapper<EntryGroup>() {

			@Override
			public EntryGroup mapRow(ResultSet rs, int rowNum) throws SQLException {
				EntryGroup grupo = new EntryGroup();
				grupo.setCodigoNaturezaDespesa(rs.getInt("codigoNaturezaDespesa"));
				grupo.setDescricaoNaturezaDespesa(rs.getString("descricaoNaturezaDespesa"));
				grupo.setLancamentoTmp(new Entry());
				grupo.getLancamentoTmp().setData(rs.getDate("dataLancamento"));
				grupo.getLancamentoTmp().setDescricao(rs.getString("descricaoLancamento"));
				grupo.getLancamentoTmp().setValor(rs.getDouble("valorLancamento"));
				return grupo;
			}
		};
	}
}
