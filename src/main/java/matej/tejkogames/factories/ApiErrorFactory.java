package matej.tejkogames.factories;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import matej.tejkogames.models.general.ApiError;
import matej.tejkogames.models.general.User;
import matej.tejkogames.utils.ApiErrorUtil;

@Component
public class ApiErrorFactory {

    public ApiError createApiError(Throwable exception) {
        ApiError apiError = new ApiError();
        apiError.setTimestamp(LocalDateTime.now());  
        apiError.setMessage(ApiErrorUtil.constructErrorMessage(exception));  
        apiError.setContent(ApiErrorUtil.constructErrorContent(exception));
        return apiError;
    }

    public ApiError createApiError(User user, Throwable exception) {
        ApiError apiError = new ApiError();
        apiError.setUser(user);
        apiError.setTimestamp(LocalDateTime.now());  
        apiError.setMessage(ApiErrorUtil.constructErrorMessage(exception));  
        apiError.setContent(ApiErrorUtil.constructErrorContent(exception));
        return apiError;
    }
    
}
