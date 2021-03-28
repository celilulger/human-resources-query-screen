import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IJobApplication, JobApplication } from '../job-application.model';

import { JobApplicationService } from './job-application.service';

describe('Service Tests', () => {
  describe('JobApplication Service', () => {
    let service: JobApplicationService;
    let httpMock: HttpTestingController;
    let elemDefault: IJobApplication;
    let expectedResult: IJobApplication | IJobApplication[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      service = TestBed.inject(JobApplicationService);
      httpMock = TestBed.inject(HttpTestingController);

      elemDefault = {
        id: 0,
        firstName: 'AAAAAAA',
        lastName: 'AAAAAAA',
        age: 0,
        gender: 'AAAAAAA',
        educationStatus: 'AAAAAAA',
        city: 'AAAAAAA',
        district: 'AAAAAAA',
        militaryStatus: 'AAAAAAA',
        language: 'AAAAAAA',
        programmingLanguage: 'AAAAAAA',
        hobbies: 'AAAAAAA',
        certificates: 'AAAAAAA',
      };
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a JobApplication', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new JobApplication()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a JobApplication', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            age: 1,
            gender: 'BBBBBB',
            educationStatus: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            militaryStatus: 'BBBBBB',
            language: 'BBBBBB',
            programmingLanguage: 'BBBBBB',
            hobbies: 'BBBBBB',
            certificates: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should partial update a JobApplication', () => {
        const patchObject = Object.assign(
          {
            lastName: 'BBBBBB',
            gender: 'BBBBBB',
            educationStatus: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            militaryStatus: 'BBBBBB',
            language: 'BBBBBB',
            programmingLanguage: 'BBBBBB',
            hobbies: 'BBBBBB',
            certificates: 'BBBBBB',
          },
          new JobApplication()
        );

        const returnedFromService = Object.assign(patchObject, elemDefault);

        const expected = Object.assign({}, returnedFromService);

        service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PATCH' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of JobApplication', () => {
        const returnedFromService = Object.assign(
          {
            id: 1,
            firstName: 'BBBBBB',
            lastName: 'BBBBBB',
            age: 1,
            gender: 'BBBBBB',
            educationStatus: 'BBBBBB',
            city: 'BBBBBB',
            district: 'BBBBBB',
            militaryStatus: 'BBBBBB',
            language: 'BBBBBB',
            programmingLanguage: 'BBBBBB',
            hobbies: 'BBBBBB',
            certificates: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a JobApplication', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });

      describe('addJobApplicationToCollectionIfMissing', () => {
        it('should add a JobApplication to an empty array', () => {
          const jobApplication: IJobApplication = { id: 123 };
          expectedResult = service.addJobApplicationToCollectionIfMissing([], jobApplication);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(jobApplication);
        });

        it('should not add a JobApplication to an array that contains it', () => {
          const jobApplication: IJobApplication = { id: 123 };
          const jobApplicationCollection: IJobApplication[] = [
            {
              ...jobApplication,
            },
            { id: 456 },
          ];
          expectedResult = service.addJobApplicationToCollectionIfMissing(jobApplicationCollection, jobApplication);
          expect(expectedResult).toHaveLength(2);
        });

        it("should add a JobApplication to an array that doesn't contain it", () => {
          const jobApplication: IJobApplication = { id: 123 };
          const jobApplicationCollection: IJobApplication[] = [{ id: 456 }];
          expectedResult = service.addJobApplicationToCollectionIfMissing(jobApplicationCollection, jobApplication);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(jobApplication);
        });

        it('should add only unique JobApplication to an array', () => {
          const jobApplicationArray: IJobApplication[] = [{ id: 123 }, { id: 456 }, { id: 77869 }];
          const jobApplicationCollection: IJobApplication[] = [{ id: 123 }];
          expectedResult = service.addJobApplicationToCollectionIfMissing(jobApplicationCollection, ...jobApplicationArray);
          expect(expectedResult).toHaveLength(3);
        });

        it('should accept varargs', () => {
          const jobApplication: IJobApplication = { id: 123 };
          const jobApplication2: IJobApplication = { id: 456 };
          expectedResult = service.addJobApplicationToCollectionIfMissing([], jobApplication, jobApplication2);
          expect(expectedResult).toHaveLength(2);
          expect(expectedResult).toContain(jobApplication);
          expect(expectedResult).toContain(jobApplication2);
        });

        it('should accept null and undefined values', () => {
          const jobApplication: IJobApplication = { id: 123 };
          expectedResult = service.addJobApplicationToCollectionIfMissing([], null, jobApplication, undefined);
          expect(expectedResult).toHaveLength(1);
          expect(expectedResult).toContain(jobApplication);
        });
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
