package com.donatus.activity_tracker_api.utils.mappers;

import com.donatus.activity_tracker_api.dto.clientDTO.ClientRegistrationDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientResponseDTO;
import com.donatus.activity_tracker_api.entity.ClientEntity;
import static com.donatus.activity_tracker_api.utils.PasswordHash.encryptPassword;

import com.donatus.activity_tracker_api.entity.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ClientDTOMapper implements ClientDTOMapperServices {

    @Override
    public ClientEntity registrationDTOMapper(ClientRegistrationDTO newClient){
        Roles role = new Roles();
        role.setName("USER");
        return ClientEntity.builder()
                .firstName(newClient.getFirstName())
                .lastName(newClient.getLastName())
                .email(newClient.getEmail())
                .password(encryptPassword(newClient.getPassword()))
                .occupation(newClient.getOccupation())
                .address(newClient.getAddress())
                .roles(new ArrayList<>(List.of(role)))
                .build();
    }

    @Override
    public ClientResponseDTO responseDTO(ClientEntity client){
        return ClientResponseDTO.builder()
                    .clientId(client.getClientId())
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .email(client.getEmail())
                    .occupation(client.getOccupation())
                    .address(client.getAddress())
                    .build();
    }
}
