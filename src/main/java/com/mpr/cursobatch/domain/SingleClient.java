package com.mpr.cursobatch.domain;


import org.hibernate.validator.constraints.Range;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingleClient {

  @NotNull
  @Size(min = 1, max = 100)
  @Pattern(regexp = "[a-zA-Z\\s]+", message = "O nome deve ser alfabético")
  private String name;

  @NotNull
  @Range(min = 18, max = 200)
  private String age;

  @NotNull
  @Size(min = 1, max = 50)
  @Pattern(regexp = "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email inválido")
  private String email;
}
