import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IJobApplication } from '../job-application.model';

@Component({
  selector: 'jhi-job-application-detail',
  templateUrl: './job-application-detail.component.html',
})
export class JobApplicationDetailComponent implements OnInit {
  jobApplication: IJobApplication | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ jobApplication }) => {
      this.jobApplication = jobApplication;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
