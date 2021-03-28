import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JobApplicationDetailComponent } from './job-application-detail.component';

describe('Component Tests', () => {
  describe('JobApplication Management Detail Component', () => {
    let comp: JobApplicationDetailComponent;
    let fixture: ComponentFixture<JobApplicationDetailComponent>;

    beforeEach(() => {
      TestBed.configureTestingModule({
        declarations: [JobApplicationDetailComponent],
        providers: [
          {
            provide: ActivatedRoute,
            useValue: { data: of({ jobApplication: { id: 123 } }) },
          },
        ],
      })
        .overrideTemplate(JobApplicationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(JobApplicationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load jobApplication on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.jobApplication).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
