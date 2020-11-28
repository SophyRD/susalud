import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFuenteReferencial } from 'app/shared/model/fuente-referencial.model';

type EntityResponseType = HttpResponse<IFuenteReferencial>;
type EntityArrayResponseType = HttpResponse<IFuenteReferencial[]>;

@Injectable({ providedIn: 'root' })
export class FuenteReferencialService {
  public resourceUrl = SERVER_API_URL + 'api/fuente-referencials';

  constructor(protected http: HttpClient) {}

  create(fuenteReferencial: IFuenteReferencial): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fuenteReferencial);
    return this.http
      .post<IFuenteReferencial>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fuenteReferencial: IFuenteReferencial): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fuenteReferencial);
    return this.http
      .put<IFuenteReferencial>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFuenteReferencial>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFuenteReferencial[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fuenteReferencial: IFuenteReferencial): IFuenteReferencial {
    const copy: IFuenteReferencial = Object.assign({}, fuenteReferencial, {
      frFechaCreacion:
        fuenteReferencial.frFechaCreacion && fuenteReferencial.frFechaCreacion.isValid()
          ? fuenteReferencial.frFechaCreacion.format(DATE_FORMAT)
          : undefined,
      frFechaModificacion:
        fuenteReferencial.frFechaModificacion && fuenteReferencial.frFechaModificacion.isValid()
          ? fuenteReferencial.frFechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.frFechaCreacion = res.body.frFechaCreacion ? moment(res.body.frFechaCreacion) : undefined;
      res.body.frFechaModificacion = res.body.frFechaModificacion ? moment(res.body.frFechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fuenteReferencial: IFuenteReferencial) => {
        fuenteReferencial.frFechaCreacion = fuenteReferencial.frFechaCreacion ? moment(fuenteReferencial.frFechaCreacion) : undefined;
        fuenteReferencial.frFechaModificacion = fuenteReferencial.frFechaModificacion
          ? moment(fuenteReferencial.frFechaModificacion)
          : undefined;
      });
    }
    return res;
  }
}
