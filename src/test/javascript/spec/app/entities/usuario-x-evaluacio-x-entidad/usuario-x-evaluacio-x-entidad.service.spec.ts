import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { UsuarioXEvaluacioXEntidadService } from 'app/entities/usuario-x-evaluacio-x-entidad/usuario-x-evaluacio-x-entidad.service';
import { IUsuarioXEvaluacioXEntidad, UsuarioXEvaluacioXEntidad } from 'app/shared/model/usuario-x-evaluacio-x-entidad.model';

describe('Service Tests', () => {
  describe('UsuarioXEvaluacioXEntidad Service', () => {
    let injector: TestBed;
    let service: UsuarioXEvaluacioXEntidadService;
    let httpMock: HttpTestingController;
    let elemDefault: IUsuarioXEvaluacioXEntidad;
    let expectedResult: IUsuarioXEvaluacioXEntidad | IUsuarioXEvaluacioXEntidad[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(UsuarioXEvaluacioXEntidadService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new UsuarioXEvaluacioXEntidad(0, 0, currentDate, currentDate, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            ueeFechaCreacion: currentDate.format(DATE_FORMAT),
            ueeFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a UsuarioXEvaluacioXEntidad', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            ueeFechaCreacion: currentDate.format(DATE_FORMAT),
            ueeFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ueeFechaCreacion: currentDate,
            ueeFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.create(new UsuarioXEvaluacioXEntidad()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a UsuarioXEvaluacioXEntidad', () => {
        const returnedFromService = Object.assign(
          {
            idUsuarioXEvaluacioXEntidad: 1,
            ueeFechaCreacion: currentDate.format(DATE_FORMAT),
            ueeFechaModificacion: currentDate.format(DATE_FORMAT),
            idUsuariosXevaluacion: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ueeFechaCreacion: currentDate,
            ueeFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of UsuarioXEvaluacioXEntidad', () => {
        const returnedFromService = Object.assign(
          {
            idUsuarioXEvaluacioXEntidad: 1,
            ueeFechaCreacion: currentDate.format(DATE_FORMAT),
            ueeFechaModificacion: currentDate.format(DATE_FORMAT),
            idUsuariosXevaluacion: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            ueeFechaCreacion: currentDate,
            ueeFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a UsuarioXEvaluacioXEntidad', () => {
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
