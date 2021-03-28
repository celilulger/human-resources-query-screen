package com.celil.humanresourcesqueryscreen.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A JobApplication.
 */
@Entity
@Table(name = "job_application")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JobApplication implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private Integer age;

    @Column(name = "gender")
    private String gender;

    @Column(name = "education_status")
    private String educationStatus;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "military_status")
    private String militaryStatus;

    @Column(name = "language")
    private String language;

    @Column(name = "programming_language")
    private String programmingLanguage;

    @Column(name = "hobbies")
    private String hobbies;

    @Column(name = "certificates")
    private String certificates;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobApplication id(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public JobApplication firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public JobApplication lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return this.age;
    }

    public JobApplication age(Integer age) {
        this.age = age;
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return this.gender;
    }

    public JobApplication gender(String gender) {
        this.gender = gender;
        return this;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEducationStatus() {
        return this.educationStatus;
    }

    public JobApplication educationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
        return this;
    }

    public void setEducationStatus(String educationStatus) {
        this.educationStatus = educationStatus;
    }

    public String getCity() {
        return this.city;
    }

    public JobApplication city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return this.district;
    }

    public JobApplication district(String district) {
        this.district = district;
        return this;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getMilitaryStatus() {
        return this.militaryStatus;
    }

    public JobApplication militaryStatus(String militaryStatus) {
        this.militaryStatus = militaryStatus;
        return this;
    }

    public void setMilitaryStatus(String militaryStatus) {
        this.militaryStatus = militaryStatus;
    }

    public String getLanguage() {
        return this.language;
    }

    public JobApplication language(String language) {
        this.language = language;
        return this;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProgrammingLanguage() {
        return this.programmingLanguage;
    }

    public JobApplication programmingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
        return this;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getHobbies() {
        return this.hobbies;
    }

    public JobApplication hobbies(String hobbies) {
        this.hobbies = hobbies;
        return this;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getCertificates() {
        return this.certificates;
    }

    public JobApplication certificates(String certificates) {
        this.certificates = certificates;
        return this;
    }

    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobApplication)) {
            return false;
        }
        return id != null && id.equals(((JobApplication) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JobApplication{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", age=" + getAge() +
            ", gender='" + getGender() + "'" +
            ", educationStatus='" + getEducationStatus() + "'" +
            ", city='" + getCity() + "'" +
            ", district='" + getDistrict() + "'" +
            ", militaryStatus='" + getMilitaryStatus() + "'" +
            ", language='" + getLanguage() + "'" +
            ", programmingLanguage='" + getProgrammingLanguage() + "'" +
            ", hobbies='" + getHobbies() + "'" +
            ", certificates='" + getCertificates() + "'" +
            "}";
    }
}
