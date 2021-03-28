import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IJobApplication, JobApplication } from '../job-application.model';
import { JobApplicationService } from '../service/job-application.service';

@Component({
  selector: 'jhi-job-application-update',
  templateUrl: './job-application-update.component.html',
})
export class JobApplicationUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    firstName: [],
    lastName: [],
    age: [],
    gender: [],
    educationStatus: [],
    city: [],
    district: [],
    militaryStatus: [],
    language: [],
    programmingLanguage: [],
    hobbies: [],
    certificates: [],
  });

  constructor(
    protected jobApplicationService: JobApplicationService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ jobApplication }) => {
      this.updateForm(jobApplication);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const jobApplication = this.createFromForm();
    if (jobApplication.id !== undefined) {
      this.subscribeToSaveResponse(this.jobApplicationService.update(jobApplication));
    } else {
      this.subscribeToSaveResponse(this.jobApplicationService.create(jobApplication));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IJobApplication>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(jobApplication: IJobApplication): void {
    this.editForm.patchValue({
      id: jobApplication.id,
      firstName: jobApplication.firstName,
      lastName: jobApplication.lastName,
      age: jobApplication.age,
      gender: jobApplication.gender,
      educationStatus: jobApplication.educationStatus,
      city: jobApplication.city,
      district: jobApplication.district,
      militaryStatus: jobApplication.militaryStatus,
      language: jobApplication.language,
      programmingLanguage: jobApplication.programmingLanguage,
      hobbies: jobApplication.hobbies,
      certificates: jobApplication.certificates,
    });
  }

  protected createFromForm(): IJobApplication {
    return {
      ...new JobApplication(),
      id: this.editForm.get(['id'])!.value,
      firstName: this.editForm.get(['firstName'])!.value,
      lastName: this.editForm.get(['lastName'])!.value,
      age: this.editForm.get(['age'])!.value,
      gender: this.editForm.get(['gender'])!.value,
      educationStatus: this.editForm.get(['educationStatus'])!.value,
      city: this.editForm.get(['city'])!.value,
      district: this.editForm.get(['district'])!.value,
      militaryStatus: this.editForm.get(['militaryStatus'])!.value,
      language: this.editForm.get(['language'])!.value,
      programmingLanguage: this.editForm.get(['programmingLanguage'])!.value,
      hobbies: this.editForm.get(['hobbies'])!.value,
      certificates: this.editForm.get(['certificates'])!.value,
    };
  }
}
