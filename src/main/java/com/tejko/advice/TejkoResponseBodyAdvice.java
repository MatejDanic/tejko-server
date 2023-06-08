package com.tejko.advice;

import java.util.Collection;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.tejko.models.general.enums.ResponseStatus;
import com.tejko.models.general.payload.ResponseWrapper;
import com.tejko.models.general.payload.responses.ApiResponse;

@ControllerAdvice
public class TejkoResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().equals(ResponseEntity.class);
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (!(body instanceof ResponseWrapper)) { // skip if body is already ResponseWrapper type (e.g. exception ocurred and was handled by ExceptionHandler advice)
            if (body instanceof Collection<?>) {
                response.getHeaders().add("X-Total-Count", String.valueOf(((Collection<?>) body).size()));
            }
            if (ApiResponse.class.isInstance(body)) {
                body = new ResponseWrapper<ApiResponse<?>>(ResponseStatus.SUCCESS, null, (ApiResponse<?>) body);
            }
        }
        return body;
    }

}