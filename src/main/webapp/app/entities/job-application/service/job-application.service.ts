import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IJobApplication, getJobApplicationIdentifier } from '../job-application.model';

export type EntityResponseType = HttpResponse<IJobApplication>;
export type EntityArrayResponseType = HttpResponse<IJobApplication[]>;

@Injectable({ providedIn: 'root' })
export class JobApplicationService {
  public resourceUrl = this.applicationConfigService.getEndpointFor('api/job-applications');

  constructor(protected http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  create(jobApplication: IJobApplication): Observable<EntityResponseType> {
    return this.http.post<IJobApplication>(this.resourceUrl, jobApplication, { observe: 'response' });
  }

  update(jobApplication: IJobApplication): Observable<EntityResponseType> {
    return this.http.put<IJobApplication>(`${this.resourceUrl}/${getJobApplicationIdentifier(jobApplication) as number}`, jobApplication, {
      observe: 'response',
    });
  }

  partialUpdate(jobApplication: IJobApplication): Observable<EntityResponseType> {
    return this.http.patch<IJobApplication>(
      `${this.resourceUrl}/${getJobApplicationIdentifier(jobApplication) as number}`,
      jobApplication,
      { observe: 'response' }
    );
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IJobApplication>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IJobApplication[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addJobApplicationToCollectionIfMissing(
    jobApplicationCollection: IJobApplication[],
    ...jobApplicationsToCheck: (IJobApplication | null | undefined)[]
  ): IJobApplication[] {
    const jobApplications: IJobApplication[] = jobApplicationsToCheck.filter(isPresent);
    if (jobApplications.length > 0) {
      const jobApplicationCollectionIdentifiers = jobApplicationCollection.map(
        jobApplicationItem => getJobApplicationIdentifier(jobApplicationItem)!
      );
      const jobApplicationsToAdd = jobApplications.filter(jobApplicationItem => {
        const jobApplicationIdentifier = getJobApplicationIdentifier(jobApplicationItem);
        if (jobApplicationIdentifier == null || jobApplicationCollectionIdentifiers.includes(jobApplicationIdentifier)) {
          return false;
        }
        jobApplicationCollectionIdentifiers.push(jobApplicationIdentifier);
        return true;
      });
      return [...jobApplicationsToAdd, ...jobApplicationCollection];
    }
    return jobApplicationCollection;
  }
}
