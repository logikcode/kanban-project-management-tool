package com.manuel.projectmanagementtool;

import com.manuel.projectmanagementtool.domain.Project;
import com.manuel.projectmanagementtool.dto.ProjectUpdateDto;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class ProjectMapper {

    public ProjectMapper(){}

    public Project mapUpdateDto(Project project,  ProjectUpdateDto updateDto){
        if (!Objects.equals(project.getProjectName(), updateDto.getProjectName())){
            project.setProjectName(updateDto.getProjectName());
        }
        if (!Objects.equals(project.getDescription(), updateDto.getDescription())){
            project.setDescription(updateDto.getDescription());
        }
        return project;
    }

}
