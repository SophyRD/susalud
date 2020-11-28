import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { VerificadorService } from 'app/entities/verificador/verificador.service';
import { IVerificador, Verificador } from 'app/shared/model/verificador.model';

describe('Service Tests', () => {
  describe('Verificador Service', () => {
    let injector: TestBed;
    let service: VerificadorService;
    let httpMock: HttpTestingController;
    let elemDefault: IVerificador;
    let expectedResult: IVerificador | IVerificador[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(VerificadorService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Verificador(0, 0, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            v1FechaCreacion: currentDate.format(DATE_FORMAT),
            v1FechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Verificador', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            v1FechaCreacion: currentDate.format(DATE_FORMAT),
            v1FechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            v1FechaCreacion: currentDate,
            v1FechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.create(new Verificador()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Verificador', () => {
        const returnedFromService = Object.assign(
          {
            idVerificador: 1,
            v1FechaCreacion: currentDate.format(DATE_FORMAT),
            v1FechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            v1FechaCreacion: currentDate,
            v1FechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Verificador', () => {
        const returnedFromService = Object.assign(
          {
            idVerificador: 1,
            v1FechaCreacion: currentDate.format(DATE_FORMAT),
            v1FechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            v1FechaCreacion: currentDate,
            v1FechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Verificador', () => {
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
