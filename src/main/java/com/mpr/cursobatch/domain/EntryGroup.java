package com.mpr.cursobatch.domain;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryGroup {
	private Integer codigoNaturezaDespesa;
	private String descricaoNaturezaDespesa;
	private List<Entry> lancamentos = new ArrayList<>();

	private Entry lancamentoTmp;

	public Double getTotal() {
		return lancamentos
				.stream()
				.mapToDouble(Entry::getValor)
				.reduce(0.0, Double::sum);
	}
}
