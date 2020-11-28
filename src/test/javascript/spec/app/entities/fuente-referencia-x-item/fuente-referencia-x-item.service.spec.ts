import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { FuenteReferenciaXItemService } from 'app/entities/fuente-referencia-x-item/fuente-referencia-x-item.service';
import { IFuenteReferenciaXItem, FuenteReferenciaXItem } from 'app/shared/model/fuente-referencia-x-item.model';

describe('Service Tests', () => {
  describe('FuenteReferenciaXItem Service', () => {
    let injector: TestBed;
    let service: FuenteReferenciaXItemService;
    let httpMock: HttpTestingController;
    let elemDefault: IFuenteReferenciaXItem;
    let expectedResult: IFuenteReferenciaXItem | IFuenteReferenciaXItem[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(FuenteReferenciaXItemService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new FuenteReferenciaXItem(0, 0, currentDate, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            friFechaCreacion: currentDate.format(DATE_FORMAT),
            friFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a FuenteReferenciaXItem', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            friFechaCreacion: currentDate.format(DATE_FORMAT),
            friFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            friFechaCreacion: currentDate,
            friFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.create(new FuenteReferenciaXItem()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a FuenteReferenciaXItem', () => {
        const returnedFromService = Object.assign(
          {
            idFuenteReferenciaXItem: 1,
            friFechaCreacion: currentDate.format(DATE_FORMAT),
            friFechaModificacion: currentDate.format(DATE_FORMAT),
            friNombre: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            friFechaCreacion: currentDate,
            friFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of FuenteReferenciaXItem', () => {
        const returnedFromService = Object.assign(
          {
            idFuenteReferenciaXItem: 1,
            friFechaCreacion: currentDate.format(DATE_FORMAT),
            friFechaModificacion: currentDate.format(DATE_FORMAT),
            friNombre: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            friFechaCreacion: currentDate,
            friFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a FuenteReferenciaXItem', () => {
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
