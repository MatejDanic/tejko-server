package com.tejko.api.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.User;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

    List<User> findAllByRolesId(UUID id);

    Boolean existsByUsername(String username);

}
