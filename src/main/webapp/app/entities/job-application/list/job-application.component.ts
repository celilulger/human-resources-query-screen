import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IJobApplication } from '../job-application.model';
import { JobApplicationService } from '../service/job-application.service';
import { JobApplicationDeleteDialogComponent } from '../delete/job-application-delete-dialog.component';

@Component({
  selector: 'jhi-job-application',
  templateUrl: './job-application.component.html',
})
export class JobApplicationComponent implements OnInit {
  jobApplications?: IJobApplication[];
  isLoading = false;

  constructor(protected jobApplicationService: JobApplicationService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.jobApplicationService.query().subscribe(
      (res: HttpResponse<IJobApplication[]>) => {
        this.isLoading = false;
        this.jobApplications = res.body ?? [];
      },
      () => {
        this.isLoading = false;
      }
    );
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(index: number, item: IJobApplication): number {
    return item.id!;
  }

  delete(jobApplication: IJobApplication): void {
    const modalRef = this.modalService.open(JobApplicationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.jobApplication = jobApplication;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}
