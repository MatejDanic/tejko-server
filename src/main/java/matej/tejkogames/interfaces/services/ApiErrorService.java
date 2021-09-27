package matej.tejkogames.interfaces.services;

import java.util.List;
import java.util.UUID;

import matej.tejkogames.models.general.ApiError;

public interface ApiErrorService {

    public ApiError getById(UUID id);

    public List<ApiError> getAll();

    public void deleteById(UUID id);

    public void deleteAll();

}
