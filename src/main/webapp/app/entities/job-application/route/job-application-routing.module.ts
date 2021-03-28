import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { JobApplicationComponent } from '../list/job-application.component';
import { JobApplicationDetailComponent } from '../detail/job-application-detail.component';
import { JobApplicationUpdateComponent } from '../update/job-application-update.component';
import { JobApplicationRoutingResolveService } from './job-application-routing-resolve.service';

const jobApplicationRoute: Routes = [
  {
    path: '',
    component: JobApplicationComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: JobApplicationDetailComponent,
    resolve: {
      jobApplication: JobApplicationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: JobApplicationUpdateComponent,
    resolve: {
      jobApplication: JobApplicationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: JobApplicationUpdateComponent,
    resolve: {
      jobApplication: JobApplicationRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(jobApplicationRoute)],
  exports: [RouterModule],
})
export class JobApplicationRoutingModule {}
