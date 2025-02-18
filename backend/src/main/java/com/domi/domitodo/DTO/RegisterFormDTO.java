package com.domi.domitodo.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class RegisterFormDTO {
    @Email
    private String email;
    @Min(8)
    private String password;
    @Min(8)
    private String username;
}
