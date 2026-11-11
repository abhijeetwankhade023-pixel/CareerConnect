package com.careerconnect.controller;

import com.careerconnect.dto.JobRequestDTO;
import com.careerconnect.dto.JobResponseDTO;
import com.careerconnect.service.JobService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter/jobs")
public class JobController {

    private final JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @PostMapping
    public JobResponseDTO createJob(
            @Valid @RequestBody JobRequestDTO request,
            Authentication authentication) {

        String email = authentication.getName();

        return jobService.createJob(request, email);
    }

    @GetMapping
    public List<JobResponseDTO> getAllJobs() {

        return jobService.getAllJobs();
    }


    @GetMapping("/{id}")
    public JobResponseDTO getJobById(@PathVariable Long id) {

        return jobService.getJobById(id);
    }


    @PutMapping("/{id}")
    public JobResponseDTO updateJob(
            @PathVariable Long id,
            @Valid @RequestBody JobRequestDTO request) {

        return jobService.updateJob(id, request);
    }


    @DeleteMapping("/{id}")
    public String deleteJob(@PathVariable Long id) {

        jobService.deleteJob(id);

        return "Job deleted successfully";
    }



}
