import { element, by, ElementFinder } from 'protractor';

export class JobApplicationComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-job-application div table .btn-danger'));
  title = element.all(by.css('jhi-job-application div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class JobApplicationUpdatePage {
  pageTitle = element(by.id('jhi-job-application-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  idInput = element(by.id('field_id'));
  firstNameInput = element(by.id('field_firstName'));
  lastNameInput = element(by.id('field_lastName'));
  ageInput = element(by.id('field_age'));
  genderInput = element(by.id('field_gender'));
  educationStatusInput = element(by.id('field_educationStatus'));
  cityInput = element(by.id('field_city'));
  districtInput = element(by.id('field_district'));
  militaryStatusInput = element(by.id('field_militaryStatus'));
  languageInput = element(by.id('field_language'));
  programmingLanguageInput = element(by.id('field_programmingLanguage'));
  hobbiesInput = element(by.id('field_hobbies'));
  certificatesInput = element(by.id('field_certificates'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setIdInput(id: string): Promise<void> {
    await this.idInput.sendKeys(id);
  }

  async getIdInput(): Promise<string> {
    return await this.idInput.getAttribute('value');
  }

  async setFirstNameInput(firstName: string): Promise<void> {
    await this.firstNameInput.sendKeys(firstName);
  }

  async getFirstNameInput(): Promise<string> {
    return await this.firstNameInput.getAttribute('value');
  }

  async setLastNameInput(lastName: string): Promise<void> {
    await this.lastNameInput.sendKeys(lastName);
  }

  async getLastNameInput(): Promise<string> {
    return await this.lastNameInput.getAttribute('value');
  }

  async setAgeInput(age: string): Promise<void> {
    await this.ageInput.sendKeys(age);
  }

  async getAgeInput(): Promise<string> {
    return await this.ageInput.getAttribute('value');
  }

  async setGenderInput(gender: string): Promise<void> {
    await this.genderInput.sendKeys(gender);
  }

  async getGenderInput(): Promise<string> {
    return await this.genderInput.getAttribute('value');
  }

  async setEducationStatusInput(educationStatus: string): Promise<void> {
    await this.educationStatusInput.sendKeys(educationStatus);
  }

  async getEducationStatusInput(): Promise<string> {
    return await this.educationStatusInput.getAttribute('value');
  }

  async setCityInput(city: string): Promise<void> {
    await this.cityInput.sendKeys(city);
  }

  async getCityInput(): Promise<string> {
    return await this.cityInput.getAttribute('value');
  }

  async setDistrictInput(district: string): Promise<void> {
    await this.districtInput.sendKeys(district);
  }

  async getDistrictInput(): Promise<string> {
    return await this.districtInput.getAttribute('value');
  }

  async setMilitaryStatusInput(militaryStatus: string): Promise<void> {
    await this.militaryStatusInput.sendKeys(militaryStatus);
  }

  async getMilitaryStatusInput(): Promise<string> {
    return await this.militaryStatusInput.getAttribute('value');
  }

  async setLanguageInput(language: string): Promise<void> {
    await this.languageInput.sendKeys(language);
  }

  async getLanguageInput(): Promise<string> {
    return await this.languageInput.getAttribute('value');
  }

  async setProgrammingLanguageInput(programmingLanguage: string): Promise<void> {
    await this.programmingLanguageInput.sendKeys(programmingLanguage);
  }

  async getProgrammingLanguageInput(): Promise<string> {
    return await this.programmingLanguageInput.getAttribute('value');
  }

  async setHobbiesInput(hobbies: string): Promise<void> {
    await this.hobbiesInput.sendKeys(hobbies);
  }

  async getHobbiesInput(): Promise<string> {
    return await this.hobbiesInput.getAttribute('value');
  }

  async setCertificatesInput(certificates: string): Promise<void> {
    await this.certificatesInput.sendKeys(certificates);
  }

  async getCertificatesInput(): Promise<string> {
    return await this.certificatesInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class JobApplicationDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-jobApplication-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-jobApplication'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
