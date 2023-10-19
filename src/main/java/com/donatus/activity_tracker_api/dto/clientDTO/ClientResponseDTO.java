package com.donatus.activity_tracker_api.dto.clientDTO;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientResponseDTO {
    private Long clientId;
    private String firstName;
    private String lastName;
    private String email;
    private String occupation;
    private String address;
}
