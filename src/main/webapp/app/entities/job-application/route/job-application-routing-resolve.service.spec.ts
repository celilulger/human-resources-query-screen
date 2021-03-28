jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IJobApplication, JobApplication } from '../job-application.model';
import { JobApplicationService } from '../service/job-application.service';

import { JobApplicationRoutingResolveService } from './job-application-routing-resolve.service';

describe('Service Tests', () => {
  describe('JobApplication routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: JobApplicationRoutingResolveService;
    let service: JobApplicationService;
    let resultJobApplication: IJobApplication | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(JobApplicationRoutingResolveService);
      service = TestBed.inject(JobApplicationService);
      resultJobApplication = undefined;
    });

    describe('resolve', () => {
      it('should return IJobApplication returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultJobApplication = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultJobApplication).toEqual({ id: 123 });
      });

      it('should return new IJobApplication if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultJobApplication = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultJobApplication).toEqual(new JobApplication());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultJobApplication = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultJobApplication).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
