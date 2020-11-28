import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { AutoevalucionXprocesoService } from 'app/entities/autoevalucion-xproceso/autoevalucion-xproceso.service';
import { IAutoevalucionXproceso, AutoevalucionXproceso } from 'app/shared/model/autoevalucion-xproceso.model';

describe('Service Tests', () => {
  describe('AutoevalucionXproceso Service', () => {
    let injector: TestBed;
    let service: AutoevalucionXprocesoService;
    let httpMock: HttpTestingController;
    let elemDefault: IAutoevalucionXproceso;
    let expectedResult: IAutoevalucionXproceso | IAutoevalucionXproceso[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AutoevalucionXprocesoService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new AutoevalucionXproceso(0, 0, currentDate, currentDate, 'AAAAAAA', 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            apFechaCreacion: currentDate.format(DATE_FORMAT),
            spFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a AutoevalucionXproceso', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            apFechaCreacion: currentDate.format(DATE_FORMAT),
            spFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            apFechaCreacion: currentDate,
            spFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.create(new AutoevalucionXproceso()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a AutoevalucionXproceso', () => {
        const returnedFromService = Object.assign(
          {
            idAutoevalucionXproceso: 1,
            apFechaCreacion: currentDate.format(DATE_FORMAT),
            spFechaModificacion: currentDate.format(DATE_FORMAT),
            apComentario: 'BBBBBB',
            apPuntuacion: 1,
            idAutoevalucion: 1,
            idMes: 1,
            idUsuariosXevaluacion: 1,
            idEstado: 1,
            idVerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            apFechaCreacion: currentDate,
            spFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of AutoevalucionXproceso', () => {
        const returnedFromService = Object.assign(
          {
            idAutoevalucionXproceso: 1,
            apFechaCreacion: currentDate.format(DATE_FORMAT),
            spFechaModificacion: currentDate.format(DATE_FORMAT),
            apComentario: 'BBBBBB',
            apPuntuacion: 1,
            idAutoevalucion: 1,
            idMes: 1,
            idUsuariosXevaluacion: 1,
            idEstado: 1,
            idVerificador: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            apFechaCreacion: currentDate,
            spFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a AutoevalucionXproceso', () => {
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
