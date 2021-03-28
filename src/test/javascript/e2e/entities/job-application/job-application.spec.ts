import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { JobApplicationComponentsPage, JobApplicationDeleteDialog, JobApplicationUpdatePage } from './job-application.page-object';

const expect = chai.expect;

describe('JobApplication e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let jobApplicationComponentsPage: JobApplicationComponentsPage;
  let jobApplicationUpdatePage: JobApplicationUpdatePage;
  let jobApplicationDeleteDialog: JobApplicationDeleteDialog;
  const username = process.env.E2E_USERNAME ?? 'admin';
  const password = process.env.E2E_PASSWORD ?? 'admin';

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing(username, password);
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load JobApplications', async () => {
    await navBarPage.goToEntity('job-application');
    jobApplicationComponentsPage = new JobApplicationComponentsPage();
    await browser.wait(ec.visibilityOf(jobApplicationComponentsPage.title), 5000);
    expect(await jobApplicationComponentsPage.getTitle()).to.eq('humanResourcesQueryScreenApp.jobApplication.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(jobApplicationComponentsPage.entities), ec.visibilityOf(jobApplicationComponentsPage.noResult)),
      1000
    );
  });

  it('should load create JobApplication page', async () => {
    await jobApplicationComponentsPage.clickOnCreateButton();
    jobApplicationUpdatePage = new JobApplicationUpdatePage();
    expect(await jobApplicationUpdatePage.getPageTitle()).to.eq('humanResourcesQueryScreenApp.jobApplication.home.createOrEditLabel');
    await jobApplicationUpdatePage.cancel();
  });

  it('should create and save JobApplications', async () => {
    const nbButtonsBeforeCreate = await jobApplicationComponentsPage.countDeleteButtons();

    await jobApplicationComponentsPage.clickOnCreateButton();

    await promise.all([
      jobApplicationUpdatePage.setFirstNameInput('firstName'),
      jobApplicationUpdatePage.setLastNameInput('lastName'),
      jobApplicationUpdatePage.setAgeInput('5'),
      jobApplicationUpdatePage.setGenderInput('gender'),
      jobApplicationUpdatePage.setEducationStatusInput('educationStatus'),
      jobApplicationUpdatePage.setCityInput('city'),
      jobApplicationUpdatePage.setDistrictInput('district'),
      jobApplicationUpdatePage.setMilitaryStatusInput('militaryStatus'),
      jobApplicationUpdatePage.setLanguageInput('language'),
      jobApplicationUpdatePage.setProgrammingLanguageInput('programmingLanguage'),
      jobApplicationUpdatePage.setHobbiesInput('hobbies'),
      jobApplicationUpdatePage.setCertificatesInput('certificates'),
    ]);

    expect(await jobApplicationUpdatePage.getFirstNameInput()).to.eq('firstName', 'Expected FirstName value to be equals to firstName');
    expect(await jobApplicationUpdatePage.getLastNameInput()).to.eq('lastName', 'Expected LastName value to be equals to lastName');
    expect(await jobApplicationUpdatePage.getAgeInput()).to.eq('5', 'Expected age value to be equals to 5');
    expect(await jobApplicationUpdatePage.getGenderInput()).to.eq('gender', 'Expected Gender value to be equals to gender');
    expect(await jobApplicationUpdatePage.getEducationStatusInput()).to.eq(
      'educationStatus',
      'Expected EducationStatus value to be equals to educationStatus'
    );
    expect(await jobApplicationUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await jobApplicationUpdatePage.getDistrictInput()).to.eq('district', 'Expected District value to be equals to district');
    expect(await jobApplicationUpdatePage.getMilitaryStatusInput()).to.eq(
      'militaryStatus',
      'Expected MilitaryStatus value to be equals to militaryStatus'
    );
    expect(await jobApplicationUpdatePage.getLanguageInput()).to.eq('language', 'Expected Language value to be equals to language');
    expect(await jobApplicationUpdatePage.getProgrammingLanguageInput()).to.eq(
      'programmingLanguage',
      'Expected ProgrammingLanguage value to be equals to programmingLanguage'
    );
    expect(await jobApplicationUpdatePage.getHobbiesInput()).to.eq('hobbies', 'Expected Hobbies value to be equals to hobbies');
    expect(await jobApplicationUpdatePage.getCertificatesInput()).to.eq(
      'certificates',
      'Expected Certificates value to be equals to certificates'
    );

    await jobApplicationUpdatePage.save();
    expect(await jobApplicationUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await jobApplicationComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last JobApplication', async () => {
    const nbButtonsBeforeDelete = await jobApplicationComponentsPage.countDeleteButtons();
    await jobApplicationComponentsPage.clickOnLastDeleteButton();

    jobApplicationDeleteDialog = new JobApplicationDeleteDialog();
    expect(await jobApplicationDeleteDialog.getDialogTitle()).to.eq('humanResourcesQueryScreenApp.jobApplication.delete.question');
    await jobApplicationDeleteDialog.clickOnConfirmButton();
    await browser.wait(ec.visibilityOf(jobApplicationComponentsPage.title), 5000);

    expect(await jobApplicationComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
