package com.manuel.projectmanagementtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {


ResponseEntity<?> handleProjectIdException(ProjectIdException projectIdException,
                                        WebRequest request){
    ProjectIdExceptionResponse pidEx = new ProjectIdExceptionResponse(projectIdException.getMessage());
    return new ResponseEntity<>(pidEx, HttpStatus.BAD_REQUEST);


}

}
