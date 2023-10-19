package com.donatus.activity_tracker_api.utils.mappers;

import com.donatus.activity_tracker_api.dto.clientDTO.ClientRegistrationDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientResponseDTO;
import com.donatus.activity_tracker_api.entity.ClientEntity;

public interface ClientDTOMapperServices {
    ClientEntity registrationDTOMapper(ClientRegistrationDTO clientReg);
    ClientResponseDTO responseDTO(ClientEntity client);
}
