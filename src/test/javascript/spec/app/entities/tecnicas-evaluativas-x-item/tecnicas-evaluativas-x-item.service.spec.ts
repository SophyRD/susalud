import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { TecnicasEvaluativasXItemService } from 'app/entities/tecnicas-evaluativas-x-item/tecnicas-evaluativas-x-item.service';
import { ITecnicasEvaluativasXItem, TecnicasEvaluativasXItem } from 'app/shared/model/tecnicas-evaluativas-x-item.model';

describe('Service Tests', () => {
  describe('TecnicasEvaluativasXItem Service', () => {
    let injector: TestBed;
    let service: TecnicasEvaluativasXItemService;
    let httpMock: HttpTestingController;
    let elemDefault: ITecnicasEvaluativasXItem;
    let expectedResult: ITecnicasEvaluativasXItem | ITecnicasEvaluativasXItem[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(TecnicasEvaluativasXItemService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new TecnicasEvaluativasXItem(0, 0, currentDate, currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            tiFechaCreacion: currentDate.format(DATE_FORMAT),
            tiFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a TecnicasEvaluativasXItem', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            tiFechaCreacion: currentDate.format(DATE_FORMAT),
            tiFechaModificacion: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            tiFechaCreacion: currentDate,
            tiFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.create(new TecnicasEvaluativasXItem()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a TecnicasEvaluativasXItem', () => {
        const returnedFromService = Object.assign(
          {
            idTecnicasEvaluativasXItem: 1,
            tiFechaCreacion: currentDate.format(DATE_FORMAT),
            tiFechaModificacion: currentDate.format(DATE_FORMAT),
            tiNombre: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            tiFechaCreacion: currentDate,
            tiFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of TecnicasEvaluativasXItem', () => {
        const returnedFromService = Object.assign(
          {
            idTecnicasEvaluativasXItem: 1,
            tiFechaCreacion: currentDate.format(DATE_FORMAT),
            tiFechaModificacion: currentDate.format(DATE_FORMAT),
            tiNombre: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            tiFechaCreacion: currentDate,
            tiFechaModificacion: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a TecnicasEvaluativasXItem', () => {
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
