import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { MacroprocesoService } from 'app/entities/macroproceso/macroproceso.service';
import { IMacroproceso, Macroproceso } from 'app/shared/model/macroproceso.model';

describe('Service Tests', () => {
  describe('Macroproceso Service', () => {
    let injector: TestBed;
    let service: MacroprocesoService;
    let httpMock: HttpTestingController;
    let elemDefault: IMacroproceso;
    let expectedResult: IMacroproceso | IMacroproceso[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MacroprocesoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Macroproceso(0, 0, currentDate, currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            maFechaCreacion: currentDate.format(DATE_FORMAT),
            maFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Macroproceso', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            maFechaCreacion: currentDate.format(DATE_FORMAT),
            maFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            maFechaCreacion: currentDate,
            maFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.create(new Macroproceso()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Macroproceso', () => {
        const returnedFromService = Object.assign(
          {
            idMacroproceso: 1,
            maFechaCreacion: currentDate.format(DATE_FORMAT),
            maFechaModificacion: currentDate.format(DATE_FORMAT),
            verificadorlIdVerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            maFechaCreacion: currentDate,
            maFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Macroproceso', () => {
        const returnedFromService = Object.assign(
          {
            idMacroproceso: 1,
            maFechaCreacion: currentDate.format(DATE_FORMAT),
            maFechaModificacion: currentDate.format(DATE_FORMAT),
            verificadorlIdVerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            maFechaCreacion: currentDate,
            maFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Macroproceso', () => {
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
