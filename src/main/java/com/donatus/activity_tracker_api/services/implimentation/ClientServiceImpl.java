package com.donatus.activity_tracker_api.services.implimentation;

import com.donatus.activity_tracker_api.dto.clientDTO.ClientLoginDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientRegistrationDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientResponseDTO;
import com.donatus.activity_tracker_api.entity.ClientEntity;
import com.donatus.activity_tracker_api.exception.ClientNotFoundException;
import com.donatus.activity_tracker_api.exception.DuplicateEmailAddressException;
import com.donatus.activity_tracker_api.exception.InvalidClientPasswordException;
import com.donatus.activity_tracker_api.repository.ClientRepository;
import com.donatus.activity_tracker_api.services.ClientServices;
import com.donatus.activity_tracker_api.utils.ClientCheck;
import com.donatus.activity_tracker_api.utils.mappers.ClientDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.donatus.activity_tracker_api.utils.PasswordHash.isPassword;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientServices {

    private final ClientRepository clientRepo;
    private final ClientDTOMapper dtoMapper;
    private final ClientCheck check;

    @Override
    public ClientResponseDTO registerClient(ClientRegistrationDTO newClient) {
        if (clientRepo.existsByEmail(newClient.getEmail())){
            throw new DuplicateEmailAddressException("Email address already in database!");
        }
        ClientEntity client = dtoMapper.registrationDTOMapper(newClient);

        ClientEntity savedClient = clientRepo.save(client);

        return dtoMapper.responseDTO(savedClient);
    }

    @Override
    public ClientResponseDTO verifyClient(ClientLoginDTO clientLoginDTO) {
        String email = clientLoginDTO.getEmail();
        String password = clientLoginDTO.getPassword();
        Optional<ClientEntity> client = clientRepo.findClientEntityByEmail(email);

        if (client.isEmpty()){
            throw new ClientNotFoundException("Email not in database.");
        }

        String hashedPassword = client.get().getPassword().replace("{bcrypt}", "");

        if (!isPassword(password, hashedPassword)){
            throw new InvalidClientPasswordException("Enter a valid password!");
        }

        return dtoMapper.responseDTO(client.get());
    }

    @Override
    public ClientResponseDTO viewClientDetails(Long clientId) {
        return dtoMapper.responseDTO(
                check.checkClient(clientId).get());
    }

    @Override
    public void deleteClient(Long clientId) {
        check.checkClient(clientId);

        clientRepo.deleteById(clientId);
    }


}
