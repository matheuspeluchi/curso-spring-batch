package com.mpr.cursobatch.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entry {
	private String descricao;
	private Date data;
	private Double valor;
}
