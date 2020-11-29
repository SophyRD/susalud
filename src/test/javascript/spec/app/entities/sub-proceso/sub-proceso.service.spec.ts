import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SubProcesoService } from 'app/entities/sub-proceso/sub-proceso.service';
import { ISubProceso, SubProceso } from 'app/shared/model/sub-proceso.model';

describe('Service Tests', () => {
  describe('SubProceso Service', () => {
    let injector: TestBed;
    let service: SubProcesoService;
    let httpMock: HttpTestingController;
    let elemDefault: ISubProceso;
    let expectedResult: ISubProceso | ISubProceso[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SubProcesoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SubProceso(0, 0, 'AAAAAAA', 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SubProceso', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SubProceso()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SubProceso', () => {
        const returnedFromService = Object.assign(
          {
            idSubproceso: 1,
            spDescripcion: 'BBBBBB',
            procesoIdProceso: 1,
            procesoMacroprocesoIdMacroproceso: 1,
            procesoMacroprocesoVerificadorIdVerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SubProceso', () => {
        const returnedFromService = Object.assign(
          {
            idSubproceso: 1,
            spDescripcion: 'BBBBBB',
            procesoIdProceso: 1,
            procesoMacroprocesoIdMacroproceso: 1,
            procesoMacroprocesoVerificadorIdVerificador: 1,
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

      it('should delete a SubProceso', () => {
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
