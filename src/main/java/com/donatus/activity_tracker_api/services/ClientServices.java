package com.donatus.activity_tracker_api.services;

import com.donatus.activity_tracker_api.dto.clientDTO.ClientLoginDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientRegistrationDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientResponseDTO;

public interface ClientServices {
    ClientResponseDTO registerClient(ClientRegistrationDTO newClient);
    ClientResponseDTO verifyClient(ClientLoginDTO clientLoginDTO);
    ClientResponseDTO viewClientDetails(Long clientId);
    void deleteClient(Long clientId);
}
