package com.sm.socialmedia.response;

import com.sm.socialmedia.infra.errors.DatosErrorValidacion;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;
    private List<DatosErrorValidacion> errors;

}
