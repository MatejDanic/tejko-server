package com.tejko.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tejko.models.general.Log;

public interface LogRepository extends JpaRepository<Log, UUID> {

	public List<Log> findAllByIdIn(List<UUID> idSet);

}
