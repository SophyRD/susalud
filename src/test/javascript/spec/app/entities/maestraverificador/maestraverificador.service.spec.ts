import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { MaestraverificadorService } from 'app/entities/maestraverificador/maestraverificador.service';
import { IMaestraverificador, Maestraverificador } from 'app/shared/model/maestraverificador.model';

describe('Service Tests', () => {
  describe('Maestraverificador Service', () => {
    let injector: TestBed;
    let service: MaestraverificadorService;
    let httpMock: HttpTestingController;
    let elemDefault: IMaestraverificador;
    let expectedResult: IMaestraverificador | IMaestraverificador[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(MaestraverificadorService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Maestraverificador(0, 0, currentDate, currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            mvFecha: currentDate.format(DATE_FORMAT),
            mvFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Maestraverificador', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            mvFecha: currentDate.format(DATE_FORMAT),
            mvFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            mvFecha: currentDate,
            mvFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.create(new Maestraverificador()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Maestraverificador', () => {
        const returnedFromService = Object.assign(
          {
            idMaestraverificador: 1,
            mvFecha: currentDate.format(DATE_FORMAT),
            mvFechaModificacion: currentDate.format(DATE_FORMAT),
            idVerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            mvFecha: currentDate,
            mvFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Maestraverificador', () => {
        const returnedFromService = Object.assign(
          {
            idMaestraverificador: 1,
            mvFecha: currentDate.format(DATE_FORMAT),
            mvFechaModificacion: currentDate.format(DATE_FORMAT),
            idVerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            mvFecha: currentDate,
            mvFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Maestraverificador', () => {
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
