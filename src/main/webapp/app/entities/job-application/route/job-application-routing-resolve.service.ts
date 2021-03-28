import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IJobApplication, JobApplication } from '../job-application.model';
import { JobApplicationService } from '../service/job-application.service';

@Injectable({ providedIn: 'root' })
export class JobApplicationRoutingResolveService implements Resolve<IJobApplication> {
  constructor(protected service: JobApplicationService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IJobApplication> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((jobApplication: HttpResponse<JobApplication>) => {
          if (jobApplication.body) {
            return of(jobApplication.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new JobApplication());
  }
}
