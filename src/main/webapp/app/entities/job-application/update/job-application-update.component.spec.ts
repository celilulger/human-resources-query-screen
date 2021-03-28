jest.mock('@angular/router');

import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { of, Subject } from 'rxjs';

import { JobApplicationService } from '../service/job-application.service';
import { IJobApplication, JobApplication } from '../job-application.model';

import { JobApplicationUpdateComponent } from './job-application-update.component';

describe('Component Tests', () => {
  describe('JobApplication Management Update Component', () => {
    let comp: JobApplicationUpdateComponent;
    let fixture: ComponentFixture<JobApplicationUpdateComponent>;
    let activatedRoute: ActivatedRoute;
    let jobApplicationService: JobApplicationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        declarations: [JobApplicationUpdateComponent],
        providers: [FormBuilder, ActivatedRoute],
      })
        .overrideTemplate(JobApplicationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(JobApplicationUpdateComponent);
      activatedRoute = TestBed.inject(ActivatedRoute);
      jobApplicationService = TestBed.inject(JobApplicationService);

      comp = fixture.componentInstance;
    });

    describe('ngOnInit', () => {
      it('Should update editForm', () => {
        const jobApplication: IJobApplication = { id: 456 };

        activatedRoute.data = of({ jobApplication });
        comp.ngOnInit();

        expect(comp.editForm.value).toEqual(expect.objectContaining(jobApplication));
      });
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const jobApplication = { id: 123 };
        spyOn(jobApplicationService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ jobApplication });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: jobApplication }));
        saveSubject.complete();

        // THEN
        expect(comp.previousState).toHaveBeenCalled();
        expect(jobApplicationService.update).toHaveBeenCalledWith(jobApplication);
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', () => {
        // GIVEN
        const saveSubject = new Subject();
        const jobApplication = new JobApplication();
        spyOn(jobApplicationService, 'create').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ jobApplication });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.next(new HttpResponse({ body: jobApplication }));
        saveSubject.complete();

        // THEN
        expect(jobApplicationService.create).toHaveBeenCalledWith(jobApplication);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).toHaveBeenCalled();
      });

      it('Should set isSaving to false on error', () => {
        // GIVEN
        const saveSubject = new Subject();
        const jobApplication = { id: 123 };
        spyOn(jobApplicationService, 'update').and.returnValue(saveSubject);
        spyOn(comp, 'previousState');
        activatedRoute.data = of({ jobApplication });
        comp.ngOnInit();

        // WHEN
        comp.save();
        expect(comp.isSaving).toEqual(true);
        saveSubject.error('This is an error!');

        // THEN
        expect(jobApplicationService.update).toHaveBeenCalledWith(jobApplication);
        expect(comp.isSaving).toEqual(false);
        expect(comp.previousState).not.toHaveBeenCalled();
      });
    });
  });
});
