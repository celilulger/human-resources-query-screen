import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IJobApplication } from '../job-application.model';
import { JobApplicationService } from '../service/job-application.service';

@Component({
  templateUrl: './job-application-delete-dialog.component.html',
})
export class JobApplicationDeleteDialogComponent {
  jobApplication?: IJobApplication;

  constructor(protected jobApplicationService: JobApplicationService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.jobApplicationService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
