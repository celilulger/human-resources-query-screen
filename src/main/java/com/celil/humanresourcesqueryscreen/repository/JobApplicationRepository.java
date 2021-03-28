package com.celil.humanresourcesqueryscreen.repository;

import com.celil.humanresourcesqueryscreen.domain.JobApplication;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the JobApplication entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {}
