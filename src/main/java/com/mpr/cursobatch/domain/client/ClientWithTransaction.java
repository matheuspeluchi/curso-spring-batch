package com.mpr.cursobatch.domain.client;

import java.util.ArrayList;
import java.util.List;
import com.mpr.cursobatch.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientWithTransaction {

  private String name;
  private String lastName;
  private String age;
  private String email;
  private List<Transaction> transactions = new ArrayList<>();
}
