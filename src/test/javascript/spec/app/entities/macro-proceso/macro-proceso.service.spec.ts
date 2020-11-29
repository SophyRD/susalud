import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { MacroProcesoService } from 'app/entities/macro-proceso/macro-proceso.service';
import { IMacroProceso, MacroProceso } from 'app/shared/model/macro-proceso.model';

describe('Service Tests', () => {
  describe('MacroProceso Service', () => {
    let injector: TestBed;
    let service: MacroProcesoService;
    let httpMock: HttpTestingController;
    let elemDefault: IMacroProceso;
    let expectedResult: IMacroProceso | IMacroProceso[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MacroProcesoService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new MacroProceso(0, 0, 'AAAAAAA', 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a MacroProceso', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new MacroProceso()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a MacroProceso', () => {
        const returnedFromService = Object.assign(
          {
            idMacroproceso: 1,
            maFechaCreacion: 'BBBBBB',
            verificadorIdFerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of MacroProceso', () => {
        const returnedFromService = Object.assign(
          {
            idMacroproceso: 1,
            maFechaCreacion: 'BBBBBB',
            verificadorIdFerificador: 1,
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

      it('should delete a MacroProceso', () => {
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
