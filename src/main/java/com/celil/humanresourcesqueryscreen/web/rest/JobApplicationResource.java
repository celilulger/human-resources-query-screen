package com.celil.humanresourcesqueryscreen.web.rest;

import com.celil.humanresourcesqueryscreen.domain.JobApplication;
import com.celil.humanresourcesqueryscreen.repository.JobApplicationRepository;
import com.celil.humanresourcesqueryscreen.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.celil.humanresourcesqueryscreen.domain.JobApplication}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class JobApplicationResource {

    private final Logger log = LoggerFactory.getLogger(JobApplicationResource.class);

    private static final String ENTITY_NAME = "jobApplication";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationResource(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    /**
     * {@code POST  /job-applications} : Create a new jobApplication.
     *
     * @param jobApplication the jobApplication to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobApplication, or with status {@code 400 (Bad Request)} if the jobApplication has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/job-applications")
    public ResponseEntity<JobApplication> createJobApplication(@RequestBody JobApplication jobApplication) throws URISyntaxException {
        log.debug("REST request to save JobApplication : {}", jobApplication);
        if (jobApplication.getId() != null) {
            throw new BadRequestAlertException("A new jobApplication cannot already have an ID", ENTITY_NAME, "idexists");
        }
        JobApplication result = jobApplicationRepository.save(jobApplication);
        return ResponseEntity
            .created(new URI("/api/job-applications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /job-applications/:id} : Updates an existing jobApplication.
     *
     * @param id the id of the jobApplication to save.
     * @param jobApplication the jobApplication to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobApplication,
     * or with status {@code 400 (Bad Request)} if the jobApplication is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jobApplication couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/job-applications/{id}")
    public ResponseEntity<JobApplication> updateJobApplication(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JobApplication jobApplication
    ) throws URISyntaxException {
        log.debug("REST request to update JobApplication : {}, {}", id, jobApplication);
        if (jobApplication.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jobApplication.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jobApplicationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JobApplication result = jobApplicationRepository.save(jobApplication);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jobApplication.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /job-applications/:id} : Partial updates given fields of an existing jobApplication, field will ignore if it is null
     *
     * @param id the id of the jobApplication to save.
     * @param jobApplication the jobApplication to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobApplication,
     * or with status {@code 400 (Bad Request)} if the jobApplication is not valid,
     * or with status {@code 404 (Not Found)} if the jobApplication is not found,
     * or with status {@code 500 (Internal Server Error)} if the jobApplication couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/job-applications/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<JobApplication> partialUpdateJobApplication(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JobApplication jobApplication
    ) throws URISyntaxException {
        log.debug("REST request to partial update JobApplication partially : {}, {}", id, jobApplication);
        if (jobApplication.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, jobApplication.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!jobApplicationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JobApplication> result = jobApplicationRepository
            .findById(jobApplication.getId())
            .map(
                existingJobApplication -> {
                    if (jobApplication.getFirstName() != null) {
                        existingJobApplication.setFirstName(jobApplication.getFirstName());
                    }
                    if (jobApplication.getLastName() != null) {
                        existingJobApplication.setLastName(jobApplication.getLastName());
                    }
                    if (jobApplication.getAge() != null) {
                        existingJobApplication.setAge(jobApplication.getAge());
                    }
                    if (jobApplication.getGender() != null) {
                        existingJobApplication.setGender(jobApplication.getGender());
                    }
                    if (jobApplication.getEducationStatus() != null) {
                        existingJobApplication.setEducationStatus(jobApplication.getEducationStatus());
                    }
                    if (jobApplication.getCity() != null) {
                        existingJobApplication.setCity(jobApplication.getCity());
                    }
                    if (jobApplication.getDistrict() != null) {
                        existingJobApplication.setDistrict(jobApplication.getDistrict());
                    }
                    if (jobApplication.getMilitaryStatus() != null) {
                        existingJobApplication.setMilitaryStatus(jobApplication.getMilitaryStatus());
                    }
                    if (jobApplication.getLanguage() != null) {
                        existingJobApplication.setLanguage(jobApplication.getLanguage());
                    }
                    if (jobApplication.getProgrammingLanguage() != null) {
                        existingJobApplication.setProgrammingLanguage(jobApplication.getProgrammingLanguage());
                    }
                    if (jobApplication.getHobbies() != null) {
                        existingJobApplication.setHobbies(jobApplication.getHobbies());
                    }
                    if (jobApplication.getCertificates() != null) {
                        existingJobApplication.setCertificates(jobApplication.getCertificates());
                    }

                    return existingJobApplication;
                }
            )
            .map(jobApplicationRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, jobApplication.getId().toString())
        );
    }

    /**
     * {@code GET  /job-applications} : get all the jobApplications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobApplications in body.
     */
    @GetMapping("/job-applications")
    public List<JobApplication> getAllJobApplications() {
        log.debug("REST request to get all JobApplications");
        return jobApplicationRepository.findAll();
    }

    /**
     * {@code GET  /job-applications/:id} : get the "id" jobApplication.
     *
     * @param id the id of the jobApplication to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jobApplication, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/job-applications/{id}")
    public ResponseEntity<JobApplication> getJobApplication(@PathVariable Long id) {
        log.debug("REST request to get JobApplication : {}", id);
        Optional<JobApplication> jobApplication = jobApplicationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(jobApplication);
    }

    /**
     * {@code DELETE  /job-applications/:id} : delete the "id" jobApplication.
     *
     * @param id the id of the jobApplication to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/job-applications/{id}")
    public ResponseEntity<Void> deleteJobApplication(@PathVariable Long id) {
        log.debug("REST request to delete JobApplication : {}", id);
        jobApplicationRepository.deleteById(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
