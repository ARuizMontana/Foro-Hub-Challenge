package com.aruizmonta.forohubchallenge.infrastructure.errors;

import com.aruizmonta.forohubchallenge.domain.dto.error.FieldError;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<String> handleJwtException(JwtException ex) {
        String errorMessage = "Error relacionado con el token JWT: " + ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handlerRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(new FieldError("message", "Error interno en el servidor.\n" + exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleFieldsNotValid(MethodArgumentNotValidException exception) {
        List<FieldError> errors = exception.getFieldErrors().stream().map(fieldError -> new FieldError(fieldError.getField(), fieldError.getDefaultMessage())).toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handlePayloadNotValid() {
        return ResponseEntity.badRequest().body(new FieldError("body", "Payload not found"));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handlerAccessDeniedException() {
        return ResponseEntity.badRequest().body(new FieldError("message", "Acceso denegado. No tienes los permisos necesarios para acceder a esta función. "));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> handlerBadCredentialsException() {
        return ResponseEntity.badRequest().body(List.of(
                new FieldError("email", "El email es incorrecto"),
                new FieldError("password", "password es incorrecto"))
        );
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<?> handlerAuthenticationCredentialsException() {
        return ResponseEntity.badRequest().body(new FieldError("password", "Acceso denegado. El email es incorrecto. "));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<FieldError> handlerExpiredException(ExpiredJwtException e) {
        System.err.println("Error en la firma del Token: " + e.getMessage());
        return new ResponseEntity<>(new FieldError("message", "Acceso denegado. El token expirado."), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerGenericException() {
        return ResponseEntity.badRequest().body(new FieldError("message", "Error interno en el servidor. Inténtelo nuevamente"));
    }
}
