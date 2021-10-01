package matej.tejkogames.api.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import matej.tejkogames.models.general.ApiError;

public interface ApiErrorRepository extends JpaRepository<ApiError, UUID> {
	
	public List<ApiError> findAllByIdIn(List<UUID> idSet);

}
