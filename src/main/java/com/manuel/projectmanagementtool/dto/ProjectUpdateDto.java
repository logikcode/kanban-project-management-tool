package com.manuel.projectmanagementtool.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Data
@NoArgsConstructor
public class ProjectUpdateDto {
    @NotBlank(message = "Project name is blank")
    private String projectName;

    @Column(updatable = false, unique = true)
    @NotBlank(message = "Project identifier is required")
    @Size(min = 2, max = 5, message = "Please use 4 to 5 characters")
    private String projectIdentifier;

    @NotBlank(message = "Project description is required")
    private String description;
}