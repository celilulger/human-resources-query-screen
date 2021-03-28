import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'job-application',
        data: { pageTitle: 'humanResourcesQueryScreenApp.jobApplication.home.title' },
        loadChildren: () => import('./job-application/job-application.module').then(m => m.JobApplicationModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
