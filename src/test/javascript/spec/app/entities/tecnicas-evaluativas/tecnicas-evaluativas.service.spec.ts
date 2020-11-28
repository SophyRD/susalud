import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { TecnicasEvaluativasService } from 'app/entities/tecnicas-evaluativas/tecnicas-evaluativas.service';
import { ITecnicasEvaluativas, TecnicasEvaluativas } from 'app/shared/model/tecnicas-evaluativas.model';

describe('Service Tests', () => {
  describe('TecnicasEvaluativas Service', () => {
    let injector: TestBed;
    let service: TecnicasEvaluativasService;
    let httpMock: HttpTestingController;
    let elemDefault: ITecnicasEvaluativas;
    let expectedResult: ITecnicasEvaluativas | ITecnicasEvaluativas[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TecnicasEvaluativasService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TecnicasEvaluativas(0, 0, 'AAAAAAA', currentDate, currentDate, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            teFechaCreacion: currentDate.format(DATE_FORMAT),
            teFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TecnicasEvaluativas', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            teFechaCreacion: currentDate.format(DATE_FORMAT),
            teFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            teFechaCreacion: currentDate,
            teFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.create(new TecnicasEvaluativas()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TecnicasEvaluativas', () => {
        const returnedFromService = Object.assign(
          {
            idTecnicasEvaluativas: 1,
            teNombre: 'BBBBBB',
            teFechaCreacion: currentDate.format(DATE_FORMAT),
            teFechaModificacion: currentDate.format(DATE_FORMAT),
            idAutoevalucionXproceso: 1,
            idAutoevalucion: 1,
            idMes: 1,
            idUsuariosXevaluacion: 1,
            idEstado: 1,
            idVerificador: 1,
            idTecnicasEvaluativasXItem: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            teFechaCreacion: currentDate,
            teFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TecnicasEvaluativas', () => {
        const returnedFromService = Object.assign(
          {
            idTecnicasEvaluativas: 1,
            teNombre: 'BBBBBB',
            teFechaCreacion: currentDate.format(DATE_FORMAT),
            teFechaModificacion: currentDate.format(DATE_FORMAT),
            idAutoevalucionXproceso: 1,
            idAutoevalucion: 1,
            idMes: 1,
            idUsuariosXevaluacion: 1,
            idEstado: 1,
            idVerificador: 1,
            idTecnicasEvaluativasXItem: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            teFechaCreacion: currentDate,
            teFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TecnicasEvaluativas', () => {
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
