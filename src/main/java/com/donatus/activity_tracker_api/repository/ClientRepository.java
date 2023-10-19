package com.donatus.activity_tracker_api.repository;

import com.donatus.activity_tracker_api.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findClientEntityByEmail(String email);
    boolean existsByEmail(String email);
}
