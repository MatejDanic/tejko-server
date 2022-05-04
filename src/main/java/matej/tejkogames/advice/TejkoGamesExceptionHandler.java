package matej.tejkogames.advice;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import matej.tejkogames.api.services.LogServiceImpl;
import matej.tejkogames.api.services.UserServiceImpl;
import matej.tejkogames.models.general.Log;
import matej.tejkogames.models.general.enums.MessageType;
import matej.tejkogames.models.general.payload.responses.MessageResponse;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TejkoGamesExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    LogServiceImpl logService;

    @Autowired
    UserServiceImpl userService;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<MessageResponse> handleException(RuntimeException exception, WebRequest request) {
        try {
            StringWriter errors = new StringWriter();
            exception.printStackTrace(new PrintWriter(errors));
            System.out.println(exception.getMessage());
            // System.out.println(errors);
            Log log = new Log(errors.toString(), userService.getByUsername(request.getRemoteUser()));
            logService.save(log);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new MessageResponse("Error", MessageType.ERROR, exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }

}