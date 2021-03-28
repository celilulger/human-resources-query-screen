import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { JobApplicationComponent } from './list/job-application.component';
import { JobApplicationDetailComponent } from './detail/job-application-detail.component';
import { JobApplicationUpdateComponent } from './update/job-application-update.component';
import { JobApplicationDeleteDialogComponent } from './delete/job-application-delete-dialog.component';
import { JobApplicationRoutingModule } from './route/job-application-routing.module';

@NgModule({
  imports: [SharedModule, JobApplicationRoutingModule],
  declarations: [
    JobApplicationComponent,
    JobApplicationDetailComponent,
    JobApplicationUpdateComponent,
    JobApplicationDeleteDialogComponent,
  ],
  entryComponents: [JobApplicationDeleteDialogComponent],
})
export class JobApplicationModule {}
