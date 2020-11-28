import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CriteriosEvaluacionService } from 'app/entities/criterios-evaluacion/criterios-evaluacion.service';
import { ICriteriosEvaluacion, CriteriosEvaluacion } from 'app/shared/model/criterios-evaluacion.model';

describe('Service Tests', () => {
  describe('CriteriosEvaluacion Service', () => {
    let injector: TestBed;
    let service: CriteriosEvaluacionService;
    let httpMock: HttpTestingController;
    let elemDefault: ICriteriosEvaluacion;
    let expectedResult: ICriteriosEvaluacion | ICriteriosEvaluacion[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CriteriosEvaluacionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new CriteriosEvaluacion(0, 0, 'AAAAAAA', currentDate, currentDate, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ceFechaCreacion: currentDate.format(DATE_FORMAT),
            ceFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CriteriosEvaluacion', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ceFechaCreacion: currentDate.format(DATE_FORMAT),
            ceFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ceFechaCreacion: currentDate,
            ceFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.create(new CriteriosEvaluacion()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CriteriosEvaluacion', () => {
        const returnedFromService = Object.assign(
          {
            idCriteriosEvaluacion: 1,
            ceNombre: 'BBBBBB',
            ceFechaCreacion: currentDate.format(DATE_FORMAT),
            ceFechaModificacion: currentDate.format(DATE_FORMAT),
            idAutoevalucionXproceso: 1,
            idAutoevalucion: 1,
            idMes: 1,
            idUsuariosXevaluacion: 1,
            idEstado: 1,
            idVerificador: 1,
            idCriteriosEvaluacionXItem: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ceFechaCreacion: currentDate,
            ceFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CriteriosEvaluacion', () => {
        const returnedFromService = Object.assign(
          {
            idCriteriosEvaluacion: 1,
            ceNombre: 'BBBBBB',
            ceFechaCreacion: currentDate.format(DATE_FORMAT),
            ceFechaModificacion: currentDate.format(DATE_FORMAT),
            idAutoevalucionXproceso: 1,
            idAutoevalucion: 1,
            idMes: 1,
            idUsuariosXevaluacion: 1,
            idEstado: 1,
            idVerificador: 1,
            idCriteriosEvaluacionXItem: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ceFechaCreacion: currentDate,
            ceFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CriteriosEvaluacion', () => {
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
