package com.mpr.cursobatch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccount {
  private Integer id;
  private Enum<Tipo> tipo;
  private Double limite;
  private String cliente_id;
}
