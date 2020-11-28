import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { FuenteReferencialService } from 'app/entities/fuente-referencial/fuente-referencial.service';
import { IFuenteReferencial, FuenteReferencial } from 'app/shared/model/fuente-referencial.model';

describe('Service Tests', () => {
  describe('FuenteReferencial Service', () => {
    let injector: TestBed;
    let service: FuenteReferencialService;
    let httpMock: HttpTestingController;
    let elemDefault: IFuenteReferencial;
    let expectedResult: IFuenteReferencial | IFuenteReferencial[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FuenteReferencialService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FuenteReferencial(0, 0, 'AAAAAAA', currentDate, currentDate, 0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            frFechaCreacion: currentDate.format(DATE_FORMAT),
            frFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FuenteReferencial', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            frFechaCreacion: currentDate.format(DATE_FORMAT),
            frFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            frFechaCreacion: currentDate,
            frFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.create(new FuenteReferencial()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FuenteReferencial', () => {
        const returnedFromService = Object.assign(
          {
            idFuenteReferencial: 1,
            frNombre: 'BBBBBB',
            frFechaCreacion: currentDate.format(DATE_FORMAT),
            frFechaModificacion: currentDate.format(DATE_FORMAT),
            idAutoevalucionXproceso: 1,
            idAutoevalucion: 1,
            idMes: 1,
            idUsuariosXevaluacion: 1,
            idEstado: 1,
            idVerificador: 1,
            idFuenteReferenciaXItem: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            frFechaCreacion: currentDate,
            frFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FuenteReferencial', () => {
        const returnedFromService = Object.assign(
          {
            idFuenteReferencial: 1,
            frNombre: 'BBBBBB',
            frFechaCreacion: currentDate.format(DATE_FORMAT),
            frFechaModificacion: currentDate.format(DATE_FORMAT),
            idAutoevalucionXproceso: 1,
            idAutoevalucion: 1,
            idMes: 1,
            idUsuariosXevaluacion: 1,
            idEstado: 1,
            idVerificador: 1,
            idFuenteReferenciaXItem: 1,
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            frFechaCreacion: currentDate,
            frFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FuenteReferencial', () => {
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
