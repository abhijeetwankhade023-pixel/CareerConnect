package com.careerconnect.service;

import com.careerconnect.dto.JobRequestDTO;
import com.careerconnect.dto.JobResponseDTO;
import com.careerconnect.entity.Job;
import com.careerconnect.entity.User;
import com.careerconnect.exception.ResourceNotFoundException;
import com.careerconnect.repository.JobRepository;
import com.careerconnect.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobService {

    private final JobRepository jobRepository;
    private final UserRepository userRepository;

    public JobService(JobRepository jobRepository,
                      UserRepository userRepository) {
        this.jobRepository = jobRepository;
        this.userRepository = userRepository;
    }

    public JobResponseDTO createJob(JobRequestDTO request, String email) {

        User recruiter = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found with email: " + email
                        ));

        Job job = new Job();

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setCompanyName(request.getCompanyName());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setSalary(request.getSalary());

        job.setRecruiter(recruiter);

        Job savedJob = jobRepository.save(job);

        return new JobResponseDTO(
                savedJob.getId(),
                savedJob.getTitle(),
                savedJob.getDescription(),
                savedJob.getCompanyName(),
                savedJob.getLocation(),
                savedJob.getJobType(),
                savedJob.getSalary(),
                savedJob.getCreatedAt(),
                savedJob.getRecruiter().getId()
        );
    }
    public List<JobResponseDTO> getAllJobs() {

        List<Job> jobs = jobRepository.findAll();

        return jobs.stream()
                .map(job -> new JobResponseDTO(
                        job.getId(),
                        job.getTitle(),
                        job.getDescription(),
                        job.getCompanyName(),
                        job.getLocation(),
                        job.getJobType(),
                        job.getSalary(),
                        job.getCreatedAt(),
                        job.getRecruiter().getId()
                ))
                .toList();
    }

    public JobResponseDTO getJobById(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + id
                        ));

        return new JobResponseDTO(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getCompanyName(),
                job.getLocation(),
                job.getJobType(),
                job.getSalary(),
                job.getCreatedAt(),
                job.getRecruiter().getId()
        );
    }


    public JobResponseDTO updateJob(Long id, JobRequestDTO request) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + id
                        ));

        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setCompanyName(request.getCompanyName());
        job.setLocation(request.getLocation());
        job.setJobType(request.getJobType());
        job.setSalary(request.getSalary());

        Job updatedJob = jobRepository.save(job);

        return new JobResponseDTO(
                updatedJob.getId(),
                updatedJob.getTitle(),
                updatedJob.getDescription(),
                updatedJob.getCompanyName(),
                updatedJob.getLocation(),
                updatedJob.getJobType(),
                updatedJob.getSalary(),
                updatedJob.getCreatedAt(),
                updatedJob.getRecruiter().getId()
        );
    }


    public void deleteJob(Long id) {

        Job job = jobRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Job not found with id: " + id
                        ));

        jobRepository.delete(job);
    }



}
