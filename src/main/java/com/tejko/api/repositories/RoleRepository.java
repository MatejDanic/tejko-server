package com.tejko.api.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {

	Optional<Role> findByLabel(String label);

}
