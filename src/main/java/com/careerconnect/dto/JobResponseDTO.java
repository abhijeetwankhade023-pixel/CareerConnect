package com.careerconnect.dto;

import java.time.LocalDateTime;

public class JobResponseDTO {


    private Long id;
    private String title;
    private String description;
    private String companyName;
    private String location;
    private String jobType;
    private String salary;
    private LocalDateTime createdAt;
    private Long recruiterId;

    public JobResponseDTO() {
    }

    public JobResponseDTO(Long id, String title, String description,
                          String companyName, String location,
                          String jobType, String salary,
                          LocalDateTime createdAt, Long recruiterId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.companyName = companyName;
        this.location = location;
        this.jobType = jobType;
        this.salary = salary;
        this.createdAt = createdAt;
        this.recruiterId = recruiterId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getLocation() {
        return location;
    }

    public String getJobType() {
        return jobType;
    }

    public String getSalary() {
        return salary;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getRecruiterId() {
        return recruiterId;
    }
}
