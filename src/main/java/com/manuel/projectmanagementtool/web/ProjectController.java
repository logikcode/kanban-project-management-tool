package com.manuel.projectmanagementtool.web;

import com.manuel.projectmanagementtool.domain.Project;
import com.manuel.projectmanagementtool.services.ErrorValidationMapService;
import com.manuel.projectmanagementtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ErrorValidationMapService errorValidationMapService;
    @PostMapping("create")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result ){

        // check if request payload has error
        if (result.hasErrors()) {
            Map<String, String> errors =  errorValidationMapService.validateRequest(result);
            return new ResponseEntity<Map<String, String>>(errors, HttpStatus.BAD_REQUEST);
        }

        Project projectDb = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(projectDb, HttpStatus.ACCEPTED);
    }

}
