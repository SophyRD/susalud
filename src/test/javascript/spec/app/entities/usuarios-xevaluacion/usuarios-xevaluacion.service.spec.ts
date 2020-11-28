import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { UsuariosXevaluacionService } from 'app/entities/usuarios-xevaluacion/usuarios-xevaluacion.service';
import { IUsuariosXevaluacion, UsuariosXevaluacion } from 'app/shared/model/usuarios-xevaluacion.model';

describe('Service Tests', () => {
  describe('UsuariosXevaluacion Service', () => {
    let injector: TestBed;
    let service: UsuariosXevaluacionService;
    let httpMock: HttpTestingController;
    let elemDefault: IUsuariosXevaluacion;
    let expectedResult: IUsuariosXevaluacion | IUsuariosXevaluacion[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UsuariosXevaluacionService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new UsuariosXevaluacion(0, 0, currentDate, currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            uFechaModificacion: currentDate.format(DATE_FORMAT),
            uFechaCreacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a UsuariosXevaluacion', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            uFechaModificacion: currentDate.format(DATE_FORMAT),
            uFechaCreacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            uFechaModificacion: currentDate,
            uFechaCreacion: currentDate,
          },
          returnedFromService
        );

        service.create(new UsuariosXevaluacion()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UsuariosXevaluacion', () => {
        const returnedFromService = Object.assign(
          {
            idUsuariosXevaluacion: 1,
            uFechaModificacion: currentDate.format(DATE_FORMAT),
            uFechaCreacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            uFechaModificacion: currentDate,
            uFechaCreacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of UsuariosXevaluacion', () => {
        const returnedFromService = Object.assign(
          {
            idUsuariosXevaluacion: 1,
            uFechaModificacion: currentDate.format(DATE_FORMAT),
            uFechaCreacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            uFechaModificacion: currentDate,
            uFechaCreacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a UsuariosXevaluacion', () => {
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
