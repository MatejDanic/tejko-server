package matej.tejkogames.advice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import matej.tejkogames.api.services.ApiErrorServiceImpl;
import matej.tejkogames.factories.ApiErrorFactory;
import matej.tejkogames.models.general.ApiError;
import matej.tejkogames.models.general.enums.MessageType;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TejkoGamesExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    ApiErrorServiceImpl apiErrorService;

    @Autowired
    ApiErrorFactory apiErrorFactory;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<MessageResponse> handleException(RuntimeException exception, WebRequest request) {
        ApiError apiError = apiErrorFactory.createApiError(exception);           
        apiErrorService.save(apiError);
        return new ResponseEntity<>(new MessageResponse("Error", MessageType.ERROR, apiError.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}