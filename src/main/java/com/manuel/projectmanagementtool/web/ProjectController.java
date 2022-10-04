package com.manuel.projectmanagementtool.web;

import com.manuel.projectmanagementtool.domain.Project;
import com.manuel.projectmanagementtool.dto.ProjectUpdateDto;
import com.manuel.projectmanagementtool.exceptions.ProjectIdException;
import com.manuel.projectmanagementtool.services.ErrorValidationMapService;
import com.manuel.projectmanagementtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Objects;
@CrossOrigin
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

    @GetMapping("/{projectId}")
    ResponseEntity<?>  getProjectById(@PathVariable("projectId") String projectId){
       Project  project = projectService.findProjectWithMatchingID(projectId.toUpperCase());
       if (project == null)
           new ProjectIdException("Project with id =>"+ projectId + "does not exist");
       return new ResponseEntity<>(project, HttpStatus.FOUND);
    }

    @GetMapping("/all")
    ResponseEntity<?> getAllProjects(){
        Iterable<Project> projects = projectService.findAllProjects();
        System.out.println(projects.toString());
        return new ResponseEntity<>(projects, HttpStatus.FOUND);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProjectById(@PathVariable("id") String identifier){
        Project project = projectService.findProjectWithMatchingID(identifier.toUpperCase());
        if (project == null){
            new ResponseEntity<>(new ProjectIdException("Project with the ID doesnt exist"), HttpStatus.BAD_REQUEST);
        }
        Long id;
        if (Objects.nonNull(project)) {
            id = project.getId();
            projectService.deleteProjectById(id);
            return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>(new ProjectIdException("project Id is not valid"), HttpStatus.NOT_FOUND);

    }

    @PutMapping("/update")
    ResponseEntity<?> updateProject( @RequestBody ProjectUpdateDto updateDto){

        Project project = projectService.updateProject(updateDto);
        if (Objects.nonNull(project)){
            return new ResponseEntity<>(project, HttpStatus.ACCEPTED);
        }
        return new  ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
