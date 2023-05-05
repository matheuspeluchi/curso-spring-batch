package com.mpr.cursobatch.domain.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

  private String name;
  private String lastName;
  private String age;
  private String email;
}
