import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMacroproceso } from 'app/shared/model/macroproceso.model';

type EntityResponseType = HttpResponse<IMacroproceso>;
type EntityArrayResponseType = HttpResponse<IMacroproceso[]>;

@Injectable({ providedIn: 'root' })
export class MacroprocesoService {
  public resourceUrl = SERVER_API_URL + 'api/macroprocesos';

  constructor(protected http: HttpClient) {}

  create(macroproceso: IMacroproceso): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(macroproceso);
    return this.http
      .post<IMacroproceso>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(macroproceso: IMacroproceso): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(macroproceso);
    return this.http
      .put<IMacroproceso>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMacroproceso>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMacroproceso[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(macroproceso: IMacroproceso): IMacroproceso {
    const copy: IMacroproceso = Object.assign({}, macroproceso, {
      maFechaCreacion:
        macroproceso.maFechaCreacion && macroproceso.maFechaCreacion.isValid()
          ? macroproceso.maFechaCreacion.format(DATE_FORMAT)
          : undefined,
      maFechaModificacion:
        macroproceso.maFechaModificacion && macroproceso.maFechaModificacion.isValid()
          ? macroproceso.maFechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.maFechaCreacion = res.body.maFechaCreacion ? moment(res.body.maFechaCreacion) : undefined;
      res.body.maFechaModificacion = res.body.maFechaModificacion ? moment(res.body.maFechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((macroproceso: IMacroproceso) => {
        macroproceso.maFechaCreacion = macroproceso.maFechaCreacion ? moment(macroproceso.maFechaCreacion) : undefined;
        macroproceso.maFechaModificacion = macroproceso.maFechaModificacion ? moment(macroproceso.maFechaModificacion) : undefined;
      });
    }
    return res;
  }
}
