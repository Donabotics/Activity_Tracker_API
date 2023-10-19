package com.donatus.activity_tracker_api.dto.clientDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ClientRegistrationDTO {
    @NotBlank(message = "*required")
    @Size(min = 3, max = 25)
    private String firstName;

    @Size(max = 25, message = "too long!")
    private String lastName;

    @Size(min = 8, max = 45)
    @NotBlank(message = "*required")
    @Email(message = "Invalid email")
    private String email;

    @Size(min = 4, max = 15)
    @NotBlank(message = "*required")
    private String password;

    @Size(min = 4, max = 15)
    @NotBlank(message = "*required")
    private String ConfirmPassword;

    @Size(max = 45, message = "too long!")
    @NotBlank(message = "*required")
    private String occupation;

    @Size(max = 45, message = "too long!")
    private String address;
}
