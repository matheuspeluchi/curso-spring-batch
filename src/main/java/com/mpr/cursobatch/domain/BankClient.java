package com.mpr.cursobatch.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankClient {

  private String nome;
  private Integer age;
  private String email;
  private Double faixa_salarial;
}
