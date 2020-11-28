import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { ProcesoService } from 'app/entities/proceso/proceso.service';
import { IProceso, Proceso } from 'app/shared/model/proceso.model';

describe('Service Tests', () => {
  describe('Proceso Service', () => {
    let injector: TestBed;
    let service: ProcesoService;
    let httpMock: HttpTestingController;
    let elemDefault: IProceso;
    let expectedResult: IProceso | IProceso[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ProcesoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Proceso(0, 0, 'AAAAAAA', currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            idMacroproceso: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Proceso', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            idMacroproceso: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            idMacroproceso: currentDate,
          },
          returnedFromService
        );

        service.create(new Proceso()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Proceso', () => {
        const returnedFromService = Object.assign(
          {
            idProceso: 1,
            prDescripcion: 'BBBBBB',
            idMacroproceso: currentDate.format(DATE_FORMAT),
            idVerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            idMacroproceso: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Proceso', () => {
        const returnedFromService = Object.assign(
          {
            idProceso: 1,
            prDescripcion: 'BBBBBB',
            idMacroproceso: currentDate.format(DATE_FORMAT),
            idVerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            idMacroproceso: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Proceso', () => {
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
