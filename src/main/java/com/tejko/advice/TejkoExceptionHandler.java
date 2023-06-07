package com.tejko.advice;

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

import com.tejko.api.services.LogService;
import com.tejko.api.services.UserService;
import com.tejko.models.general.enums.LogLevel;
import com.tejko.models.general.enums.ResponseStatus;
import com.tejko.models.general.payload.ResponseWrapper;
import com.tejko.models.general.payload.requests.LogRequest;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TejkoExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    UserService userService;

    @Autowired
    LogService logService;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseWrapper<?>> handleException(RuntimeException exception, WebRequest request) {
        try {
            StringWriter errors = new StringWriter();
            exception.printStackTrace(new PrintWriter(errors));
            exception.printStackTrace(System.out);
            System.out.println(errors);
            logService.create(new LogRequest(
                userService.getByUsername(request.getRemoteUser()).getId(),
                LogLevel.ERROR, 
                errors.toString()
            ));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(new ResponseWrapper<>(ResponseStatus.ERROR, exception.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        
    }

}