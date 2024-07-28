package com.sm.socialmedia.infra.errors;

import com.sm.socialmedia.response.ApiResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class TratarErrores {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> tratarErroresDeValidacion(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violaciones = e.getConstraintViolations();
        List<DatosErrorValidacion> errs = violaciones.stream()
                .map(v -> new DatosErrorValidacion(v.getMessage()))
                .collect(Collectors.toList());
        ApiResponse errores = new ApiResponse(false, null, errs);
        return ResponseEntity.badRequest().body(errores);

    }
}
