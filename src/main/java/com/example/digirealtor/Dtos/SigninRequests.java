package com.example.digirealtor.Dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class SigninRequests {
  @NotBlank(message = "Please enter fullname")
  private String fullName;
  @NotBlank(message = "Please enter email")
  private String email;
  @NotBlank(message = "Please enter phone")
  private String phone;
  @NotBlank(message = "Please enter password")
  private String password;
  @NotBlank(message = "Please enter type of user")
  private String type;
}
