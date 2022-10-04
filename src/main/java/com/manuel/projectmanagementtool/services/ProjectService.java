package com.manuel.projectmanagementtool.services;

import com.manuel.projectmanagementtool.ProjectMapper;
import com.manuel.projectmanagementtool.domain.Project;
import com.manuel.projectmanagementtool.dto.ProjectUpdateDto;
import com.manuel.projectmanagementtool.exceptions.ProjectIdException;
import com.manuel.projectmanagementtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectMapper mapper;
    public Project saveOrUpdateProject(Project project){
        // wrap the call to save in try / catch in case of exception
        // throw exception if issues in saving occurs
        // return the exception method to the caller of this method
        try {
            String projectIdToUpper = project.getProjectIdentifier().toUpperCase();
             project.setProjectIdentifier(projectIdToUpper);
          return   projectRepository.save(project);

        } catch (Exception pidEx){
            throw new ProjectIdException("Project ID with ID "+ project.getProjectIdentifier().toUpperCase() +"already exist");
        }
    }

    // method to find a project in a database with an ID
    //return the project with matching ID
    public Project findProjectWithMatchingID(String id){
        // delegate the database search to the dao
        Project project = projectRepository.findByProjectIdentifier(id);
        return project;
    }

    public Iterable<Project> findAllProjects(){
       return projectRepository.findAll();
    }

    public void deleteProjectById(Long id){
        projectRepository.deleteById(id);
    }

    public Project updateProject(ProjectUpdateDto updateDto){
        // find the matching project identifier in the db
        // get the project out
        // update the project object and save the it back to db
       Project project = projectRepository.findByProjectIdentifier (updateDto.getProjectIdentifier());
       Project updatedProject;
       if (project != null){
          updatedProject = mapper.mapUpdateDto(project, updateDto);
         return   projectRepository.save(updatedProject);
       }
        return null;
    }

}
