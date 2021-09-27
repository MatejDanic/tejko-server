package matej.tejkogames.api.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import matej.tejkogames.api.repositories.ApiErrorRepository;
import matej.tejkogames.factories.ApiErrorFactory;
import matej.tejkogames.interfaces.services.ApiErrorService;
import matej.tejkogames.models.general.ApiError;
import matej.tejkogames.models.general.User;

@Service
public class ApiErrorServiceImpl implements ApiErrorService {

    @Autowired
    ApiErrorRepository apiErrorRepository;

    @Autowired
    ApiErrorFactory apiErrorFactory;

    public ApiError save(ApiError apiError) {
        return apiErrorRepository.save(apiError);
    }

    public ApiError save(Throwable exception) {
        ApiError apiError = apiErrorFactory.createApiError(exception);
        return apiErrorRepository.save(apiError);
    }

    public ApiError save(User user, Throwable exception) {
        ApiError apiError = apiErrorFactory.createApiError(exception);
        return apiErrorRepository.save(apiError);
    }

    @Override
    public ApiError getById(UUID id) {
        return apiErrorRepository.getById(id);
    }

    @Override
    public List<ApiError> getAll() {
        return apiErrorRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        apiErrorRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        apiErrorRepository.deleteAll();
    }

}
