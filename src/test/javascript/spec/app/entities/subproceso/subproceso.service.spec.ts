import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SubprocesoService } from 'app/entities/subproceso/subproceso.service';
import { ISubproceso, Subproceso } from 'app/shared/model/subproceso.model';

describe('Service Tests', () => {
  describe('Subproceso Service', () => {
    let injector: TestBed;
    let service: SubprocesoService;
    let httpMock: HttpTestingController;
    let elemDefault: ISubproceso;
    let expectedResult: ISubproceso | ISubproceso[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SubprocesoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Subproceso(0, 0, 'AAAAAAA', 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Subproceso', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Subproceso()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Subproceso', () => {
        const returnedFromService = Object.assign(
          {
            idSubproceso: 1,
            spDescripcion: 'BBBBBB',
            idProceso: 1,
            idMacroproceso: 1,
            idVerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Subproceso', () => {
        const returnedFromService = Object.assign(
          {
            idSubproceso: 1,
            spDescripcion: 'BBBBBB',
            idProceso: 1,
            idMacroproceso: 1,
            idVerificador: 1,
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

      it('should delete a Subproceso', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
