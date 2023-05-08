package com.mpr.cursobatch.domain.bank;

public enum Tipo {
  PRATA, OURO, PLATINA, DIAMANTE, INVALIDA;

  public static Tipo fromFaixaSalarial(Double faixaSalarial) {
    if (faixaSalarial == null)
      return INVALIDA;
    if (faixaSalarial <= 3000)
      return PRATA;
    else if (faixaSalarial > 3000 && faixaSalarial <= 5000)
      return OURO;
    else if (faixaSalarial > 5000 && faixaSalarial <= 10000)
      return PLATINA;
    else
      return DIAMANTE;
  }
}
