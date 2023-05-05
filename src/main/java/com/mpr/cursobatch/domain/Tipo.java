package com.mpr.cursobatch.domain;

public enum Tipo {
  PRATA, OURO, PLATINA, DIAMANTE;

  public static Tipo fromFaixaSalarial(double faixaSalarial) {
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
