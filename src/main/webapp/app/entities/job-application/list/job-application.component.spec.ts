import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { JobApplicationService } from '../service/job-application.service';

import { JobApplicationComponent } from './job-application.component';

describe('Component Tests', () => {
  describe('JobApplication Management Component', () => {
    let comp: JobApplicationComponent;
    let fixture: ComponentFixture<JobApplicationComponent>;
    let service: JobApplicationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [JobApplicationComponent],
      })
        .overrideTemplate(JobApplicationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(JobApplicationComponent);
      comp = fixture.componentInstance;
      service = TestBed.inject(JobApplicationService);

      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [{ id: 123 }],
            headers,
          })
        )
      );
    });

    it('Should call load all on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.jobApplications?.[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
