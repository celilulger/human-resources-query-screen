package com.celil.humanresourcesqueryscreen.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.celil.humanresourcesqueryscreen.IntegrationTest;
import com.celil.humanresourcesqueryscreen.domain.JobApplication;
import com.celil.humanresourcesqueryscreen.repository.JobApplicationRepository;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link JobApplicationResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class JobApplicationResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final String DEFAULT_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_EDUCATION_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_EDUCATION_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_DISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_DISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_MILITARY_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_MILITARY_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_PROGRAMMING_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_PROGRAMMING_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_HOBBIES = "AAAAAAAAAA";
    private static final String UPDATED_HOBBIES = "BBBBBBBBBB";

    private static final String DEFAULT_CERTIFICATES = "AAAAAAAAAA";
    private static final String UPDATED_CERTIFICATES = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/job-applications";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restJobApplicationMockMvc;

    private JobApplication jobApplication;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JobApplication createEntity(EntityManager em) {
        JobApplication jobApplication = new JobApplication()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .age(DEFAULT_AGE)
            .gender(DEFAULT_GENDER)
            .educationStatus(DEFAULT_EDUCATION_STATUS)
            .city(DEFAULT_CITY)
            .district(DEFAULT_DISTRICT)
            .militaryStatus(DEFAULT_MILITARY_STATUS)
            .language(DEFAULT_LANGUAGE)
            .programmingLanguage(DEFAULT_PROGRAMMING_LANGUAGE)
            .hobbies(DEFAULT_HOBBIES)
            .certificates(DEFAULT_CERTIFICATES);
        return jobApplication;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static JobApplication createUpdatedEntity(EntityManager em) {
        JobApplication jobApplication = new JobApplication()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .educationStatus(UPDATED_EDUCATION_STATUS)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .militaryStatus(UPDATED_MILITARY_STATUS)
            .language(UPDATED_LANGUAGE)
            .programmingLanguage(UPDATED_PROGRAMMING_LANGUAGE)
            .hobbies(UPDATED_HOBBIES)
            .certificates(UPDATED_CERTIFICATES);
        return jobApplication;
    }

    @BeforeEach
    public void initTest() {
        jobApplication = createEntity(em);
    }

    @Test
    @Transactional
    void createJobApplication() throws Exception {
        int databaseSizeBeforeCreate = jobApplicationRepository.findAll().size();
        // Create the JobApplication
        restJobApplicationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jobApplication))
            )
            .andExpect(status().isCreated());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeCreate + 1);
        JobApplication testJobApplication = jobApplicationList.get(jobApplicationList.size() - 1);
        assertThat(testJobApplication.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testJobApplication.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testJobApplication.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testJobApplication.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testJobApplication.getEducationStatus()).isEqualTo(DEFAULT_EDUCATION_STATUS);
        assertThat(testJobApplication.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testJobApplication.getDistrict()).isEqualTo(DEFAULT_DISTRICT);
        assertThat(testJobApplication.getMilitaryStatus()).isEqualTo(DEFAULT_MILITARY_STATUS);
        assertThat(testJobApplication.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testJobApplication.getProgrammingLanguage()).isEqualTo(DEFAULT_PROGRAMMING_LANGUAGE);
        assertThat(testJobApplication.getHobbies()).isEqualTo(DEFAULT_HOBBIES);
        assertThat(testJobApplication.getCertificates()).isEqualTo(DEFAULT_CERTIFICATES);
    }

    @Test
    @Transactional
    void createJobApplicationWithExistingId() throws Exception {
        // Create the JobApplication with an existing ID
        jobApplication.setId(1L);

        int databaseSizeBeforeCreate = jobApplicationRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restJobApplicationMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jobApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllJobApplications() throws Exception {
        // Initialize the database
        jobApplicationRepository.saveAndFlush(jobApplication);

        // Get all the jobApplicationList
        restJobApplicationMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(jobApplication.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER)))
            .andExpect(jsonPath("$.[*].educationStatus").value(hasItem(DEFAULT_EDUCATION_STATUS)))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY)))
            .andExpect(jsonPath("$.[*].district").value(hasItem(DEFAULT_DISTRICT)))
            .andExpect(jsonPath("$.[*].militaryStatus").value(hasItem(DEFAULT_MILITARY_STATUS)))
            .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE)))
            .andExpect(jsonPath("$.[*].programmingLanguage").value(hasItem(DEFAULT_PROGRAMMING_LANGUAGE)))
            .andExpect(jsonPath("$.[*].hobbies").value(hasItem(DEFAULT_HOBBIES)))
            .andExpect(jsonPath("$.[*].certificates").value(hasItem(DEFAULT_CERTIFICATES)));
    }

    @Test
    @Transactional
    void getJobApplication() throws Exception {
        // Initialize the database
        jobApplicationRepository.saveAndFlush(jobApplication);

        // Get the jobApplication
        restJobApplicationMockMvc
            .perform(get(ENTITY_API_URL_ID, jobApplication.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(jobApplication.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER))
            .andExpect(jsonPath("$.educationStatus").value(DEFAULT_EDUCATION_STATUS))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY))
            .andExpect(jsonPath("$.district").value(DEFAULT_DISTRICT))
            .andExpect(jsonPath("$.militaryStatus").value(DEFAULT_MILITARY_STATUS))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE))
            .andExpect(jsonPath("$.programmingLanguage").value(DEFAULT_PROGRAMMING_LANGUAGE))
            .andExpect(jsonPath("$.hobbies").value(DEFAULT_HOBBIES))
            .andExpect(jsonPath("$.certificates").value(DEFAULT_CERTIFICATES));
    }

    @Test
    @Transactional
    void getNonExistingJobApplication() throws Exception {
        // Get the jobApplication
        restJobApplicationMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewJobApplication() throws Exception {
        // Initialize the database
        jobApplicationRepository.saveAndFlush(jobApplication);

        int databaseSizeBeforeUpdate = jobApplicationRepository.findAll().size();

        // Update the jobApplication
        JobApplication updatedJobApplication = jobApplicationRepository.findById(jobApplication.getId()).get();
        // Disconnect from session so that the updates on updatedJobApplication are not directly saved in db
        em.detach(updatedJobApplication);
        updatedJobApplication
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .educationStatus(UPDATED_EDUCATION_STATUS)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .militaryStatus(UPDATED_MILITARY_STATUS)
            .language(UPDATED_LANGUAGE)
            .programmingLanguage(UPDATED_PROGRAMMING_LANGUAGE)
            .hobbies(UPDATED_HOBBIES)
            .certificates(UPDATED_CERTIFICATES);

        restJobApplicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedJobApplication.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(updatedJobApplication))
            )
            .andExpect(status().isOk());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeUpdate);
        JobApplication testJobApplication = jobApplicationList.get(jobApplicationList.size() - 1);
        assertThat(testJobApplication.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testJobApplication.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testJobApplication.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testJobApplication.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testJobApplication.getEducationStatus()).isEqualTo(UPDATED_EDUCATION_STATUS);
        assertThat(testJobApplication.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testJobApplication.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testJobApplication.getMilitaryStatus()).isEqualTo(UPDATED_MILITARY_STATUS);
        assertThat(testJobApplication.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testJobApplication.getProgrammingLanguage()).isEqualTo(UPDATED_PROGRAMMING_LANGUAGE);
        assertThat(testJobApplication.getHobbies()).isEqualTo(UPDATED_HOBBIES);
        assertThat(testJobApplication.getCertificates()).isEqualTo(UPDATED_CERTIFICATES);
    }

    @Test
    @Transactional
    void putNonExistingJobApplication() throws Exception {
        int databaseSizeBeforeUpdate = jobApplicationRepository.findAll().size();
        jobApplication.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJobApplicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, jobApplication.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jobApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchJobApplication() throws Exception {
        int databaseSizeBeforeUpdate = jobApplicationRepository.findAll().size();
        jobApplication.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJobApplicationMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(jobApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamJobApplication() throws Exception {
        int databaseSizeBeforeUpdate = jobApplicationRepository.findAll().size();
        jobApplication.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJobApplicationMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(jobApplication)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateJobApplicationWithPatch() throws Exception {
        // Initialize the database
        jobApplicationRepository.saveAndFlush(jobApplication);

        int databaseSizeBeforeUpdate = jobApplicationRepository.findAll().size();

        // Update the jobApplication using partial update
        JobApplication partialUpdatedJobApplication = new JobApplication();
        partialUpdatedJobApplication.setId(jobApplication.getId());

        partialUpdatedJobApplication
            .lastName(UPDATED_LAST_NAME)
            .gender(UPDATED_GENDER)
            .educationStatus(UPDATED_EDUCATION_STATUS)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .militaryStatus(UPDATED_MILITARY_STATUS)
            .language(UPDATED_LANGUAGE)
            .programmingLanguage(UPDATED_PROGRAMMING_LANGUAGE)
            .hobbies(UPDATED_HOBBIES)
            .certificates(UPDATED_CERTIFICATES);

        restJobApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJobApplication.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJobApplication))
            )
            .andExpect(status().isOk());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeUpdate);
        JobApplication testJobApplication = jobApplicationList.get(jobApplicationList.size() - 1);
        assertThat(testJobApplication.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testJobApplication.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testJobApplication.getAge()).isEqualTo(DEFAULT_AGE);
        assertThat(testJobApplication.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testJobApplication.getEducationStatus()).isEqualTo(UPDATED_EDUCATION_STATUS);
        assertThat(testJobApplication.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testJobApplication.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testJobApplication.getMilitaryStatus()).isEqualTo(UPDATED_MILITARY_STATUS);
        assertThat(testJobApplication.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testJobApplication.getProgrammingLanguage()).isEqualTo(UPDATED_PROGRAMMING_LANGUAGE);
        assertThat(testJobApplication.getHobbies()).isEqualTo(UPDATED_HOBBIES);
        assertThat(testJobApplication.getCertificates()).isEqualTo(UPDATED_CERTIFICATES);
    }

    @Test
    @Transactional
    void fullUpdateJobApplicationWithPatch() throws Exception {
        // Initialize the database
        jobApplicationRepository.saveAndFlush(jobApplication);

        int databaseSizeBeforeUpdate = jobApplicationRepository.findAll().size();

        // Update the jobApplication using partial update
        JobApplication partialUpdatedJobApplication = new JobApplication();
        partialUpdatedJobApplication.setId(jobApplication.getId());

        partialUpdatedJobApplication
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .age(UPDATED_AGE)
            .gender(UPDATED_GENDER)
            .educationStatus(UPDATED_EDUCATION_STATUS)
            .city(UPDATED_CITY)
            .district(UPDATED_DISTRICT)
            .militaryStatus(UPDATED_MILITARY_STATUS)
            .language(UPDATED_LANGUAGE)
            .programmingLanguage(UPDATED_PROGRAMMING_LANGUAGE)
            .hobbies(UPDATED_HOBBIES)
            .certificates(UPDATED_CERTIFICATES);

        restJobApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedJobApplication.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedJobApplication))
            )
            .andExpect(status().isOk());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeUpdate);
        JobApplication testJobApplication = jobApplicationList.get(jobApplicationList.size() - 1);
        assertThat(testJobApplication.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testJobApplication.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testJobApplication.getAge()).isEqualTo(UPDATED_AGE);
        assertThat(testJobApplication.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testJobApplication.getEducationStatus()).isEqualTo(UPDATED_EDUCATION_STATUS);
        assertThat(testJobApplication.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testJobApplication.getDistrict()).isEqualTo(UPDATED_DISTRICT);
        assertThat(testJobApplication.getMilitaryStatus()).isEqualTo(UPDATED_MILITARY_STATUS);
        assertThat(testJobApplication.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testJobApplication.getProgrammingLanguage()).isEqualTo(UPDATED_PROGRAMMING_LANGUAGE);
        assertThat(testJobApplication.getHobbies()).isEqualTo(UPDATED_HOBBIES);
        assertThat(testJobApplication.getCertificates()).isEqualTo(UPDATED_CERTIFICATES);
    }

    @Test
    @Transactional
    void patchNonExistingJobApplication() throws Exception {
        int databaseSizeBeforeUpdate = jobApplicationRepository.findAll().size();
        jobApplication.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restJobApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, jobApplication.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jobApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchJobApplication() throws Exception {
        int databaseSizeBeforeUpdate = jobApplicationRepository.findAll().size();
        jobApplication.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJobApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(jobApplication))
            )
            .andExpect(status().isBadRequest());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamJobApplication() throws Exception {
        int databaseSizeBeforeUpdate = jobApplicationRepository.findAll().size();
        jobApplication.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restJobApplicationMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(jobApplication))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the JobApplication in the database
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteJobApplication() throws Exception {
        // Initialize the database
        jobApplicationRepository.saveAndFlush(jobApplication);

        int databaseSizeBeforeDelete = jobApplicationRepository.findAll().size();

        // Delete the jobApplication
        restJobApplicationMockMvc
            .perform(delete(ENTITY_API_URL_ID, jobApplication.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<JobApplication> jobApplicationList = jobApplicationRepository.findAll();
        assertThat(jobApplicationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
