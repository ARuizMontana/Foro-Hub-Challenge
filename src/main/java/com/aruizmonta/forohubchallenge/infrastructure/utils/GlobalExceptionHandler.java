package com.aruizmonta.forohubchallenge.infrastructure.utils;

import com.aruizmonta.forohubchallenge.domain.dto.error.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException(Exception exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getError(exception, request, "Error interno en el servidor. Inténtelo nuevamente"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerAccessDeniedException(HttpServletRequest request, AccessDeniedException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getError(exception, request, "Acceso denegado. No tienes los permisos necesarios para acceder a esta función. "));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(getError(exception, request, "Error en la estructura de la petición"));
    }

    private ApiError getError(Exception exception, HttpServletRequest request, String message) {
        ApiError error = new ApiError();
        error.setBackendMessage(exception.getLocalizedMessage());
        error.setUrl(request.getRequestURL().toString());
        error.setMethod(request.getMethod());
        error.setMessage(message);
        error.setTimestamp(LocalDateTime.now());
        return error;
    }
}
