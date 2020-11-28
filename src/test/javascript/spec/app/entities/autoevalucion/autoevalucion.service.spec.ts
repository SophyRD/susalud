import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { AutoevalucionService } from 'app/entities/autoevalucion/autoevalucion.service';
import { IAutoevalucion, Autoevalucion } from 'app/shared/model/autoevalucion.model';

describe('Service Tests', () => {
  describe('Autoevalucion Service', () => {
    let injector: TestBed;
    let service: AutoevalucionService;
    let httpMock: HttpTestingController;
    let elemDefault: IAutoevalucion;
    let expectedResult: IAutoevalucion | IAutoevalucion[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(AutoevalucionService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Autoevalucion(0, 0, 'AAAAAAA', 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Autoevalucion', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Autoevalucion()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Autoevalucion', () => {
        const returnedFromService = Object.assign(
          {
            idAutoevalucion: 1,
            aAvance: 'BBBBBB',
            idMes: 1,
            idUsuariosXevaluacion: 1,
            idEstado: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Autoevalucion', () => {
        const returnedFromService = Object.assign(
          {
            idAutoevalucion: 1,
            aAvance: 'BBBBBB',
            idMes: 1,
            idUsuariosXevaluacion: 1,
            idEstado: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Autoevalucion', () => {
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
