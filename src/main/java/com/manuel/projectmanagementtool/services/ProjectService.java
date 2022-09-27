package com.manuel.projectmanagementtool.services;

import com.manuel.projectmanagementtool.domain.Project;
import com.manuel.projectmanagementtool.dto.ProjectDto;
import com.manuel.projectmanagementtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){

        return projectRepository.save(project);
    }
}
