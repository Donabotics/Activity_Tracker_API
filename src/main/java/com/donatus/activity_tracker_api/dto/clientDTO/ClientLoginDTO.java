package com.donatus.activity_tracker_api.dto.clientDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClientLoginDTO {

    @Size(min = 8, max = 45)
    @NotBlank(message = "*required")
    @Email(message = "Invalid email")
    private String email;

    @Size(min = 4, max = 15)
    @NotBlank(message = "*required")
    private String password;
}
