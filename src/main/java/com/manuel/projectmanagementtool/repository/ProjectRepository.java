package com.manuel.projectmanagementtool.repository;

import com.manuel.projectmanagementtool.domain.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    Project findByProjectIdentifier(String id);

    @Override
    Iterable<Project> findAll();



}
