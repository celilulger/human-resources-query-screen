package com.celil.humanresourcesqueryscreen.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.celil.humanresourcesqueryscreen.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JobApplicationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(JobApplication.class);
        JobApplication jobApplication1 = new JobApplication();
        jobApplication1.setId(1L);
        JobApplication jobApplication2 = new JobApplication();
        jobApplication2.setId(jobApplication1.getId());
        assertThat(jobApplication1).isEqualTo(jobApplication2);
        jobApplication2.setId(2L);
        assertThat(jobApplication1).isNotEqualTo(jobApplication2);
        jobApplication1.setId(null);
        assertThat(jobApplication1).isNotEqualTo(jobApplication2);
    }
}
