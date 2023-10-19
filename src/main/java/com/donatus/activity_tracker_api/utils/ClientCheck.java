package com.donatus.activity_tracker_api.utils;

import com.donatus.activity_tracker_api.entity.ClientEntity;
import com.donatus.activity_tracker_api.exception.ClientNotFoundException;
import com.donatus.activity_tracker_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClientCheck {

    private final ClientRepository clientRepo;

    public   Optional<ClientEntity> checkClient(Long clientId){
        Optional<ClientEntity> client = clientRepo.findById(clientId);
        if (client.isEmpty()){
            throw new ClientNotFoundException("Client with id: "+clientId+" does not exist");
        }

        return client;
    }
}
