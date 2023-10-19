package com.donatus.activity_tracker_api.config;

import com.donatus.activity_tracker_api.entity.ClientEntity;
import com.donatus.activity_tracker_api.entity.Roles;
import com.donatus.activity_tracker_api.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServices implements UserDetailsService {

    private final ClientRepository clientRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ClientEntity client = clientRepo.findClientEntityByEmail(email)
                                        .orElseThrow(() -> new UsernameNotFoundException("User not Found"));

        return new User(client.getEmail(), client.getPassword(), mapRolesToAuthorities(client.getRoles()));
    }

    public Collection<GrantedAuthority> mapRolesToAuthorities(List<Roles> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
