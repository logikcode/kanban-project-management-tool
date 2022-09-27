package com.manuel.projectmanagementtool.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ErrorValidationMapService {

    public Map<String, String> validateRequest(BindingResult result) {

        Map<String, String> errorFields;
        if (result.hasErrors()) {
            errorFields = new HashMap<>();

            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                String errorField = fieldError.getField();
                String errorMsg = fieldError.getDefaultMessage();
                errorFields.put(errorField, errorMsg);
            }
            return errorFields;
        }
        return null;
    }
}
