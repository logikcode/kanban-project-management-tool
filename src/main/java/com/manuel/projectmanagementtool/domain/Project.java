package com.manuel.projectmanagementtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is blank")
    private String projectName;

    @Column(updatable = false, unique = true)
   @NotBlank(message = "Project identifier is required")
   @Size(min = 2, max = 5, message = "Please use 4 to 5 characters")
    private String projectIdentifier;

    @NotBlank(message = "Project description is required")
    private String description;

    @JsonFormat(pattern = "yyy-mm-dd")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "yyy-mm-dd")
    private LocalDateTime endDate;

    @JsonFormat(pattern = "yyy-mm-dd")
    private Date createdAt;
    @JsonFormat(pattern = "yyy-mm-dd")
    private Date updatedAt;


    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }



}
