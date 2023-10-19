package com.donatus.activity_tracker_api.rest;

import com.donatus.activity_tracker_api.config.JWTGenerator;
import com.donatus.activity_tracker_api.dto.AuthResponseDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientLoginDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientRegistrationDTO;
import com.donatus.activity_tracker_api.dto.clientDTO.ClientResponseDTO;
import com.donatus.activity_tracker_api.services.ClientServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/client")
public class LoginAndRegController {

    private final ClientServices services;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    @PostMapping("/signup")
    public ResponseEntity<ClientResponseDTO> registerClient(@RequestBody ClientRegistrationDTO clientDTO){
        return ResponseEntity.ok(services.registerClient(clientDTO));
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientResponseDTO> viewClient(@PathVariable Long clientId){
        return ResponseEntity.ok(services.viewClientDetails(clientId));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> verifyClient(@RequestBody ClientLoginDTO clientLoginDTO){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(clientLoginDTO.getEmail(), clientLoginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new  ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

//    @PostMapping("/login")
//    public ResponseEntity<ClientResponseDTO> verifyClient(@RequestBody ClientLoginDTO clientLoginDTO){
////        Authentication authentication = aut
//        return ResponseEntity.ok(services.verifyClient(clientLoginDTO));
//    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<String> deleteClient(@PathVariable Long clientId){
        services.deleteClient(clientId);

        return ResponseEntity.ok("Client with id: "+clientId+" deleted!");
    }

}
