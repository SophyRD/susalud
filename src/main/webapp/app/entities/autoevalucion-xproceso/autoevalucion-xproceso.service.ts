import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAutoevalucionXproceso } from 'app/shared/model/autoevalucion-xproceso.model';

type EntityResponseType = HttpResponse<IAutoevalucionXproceso>;
type EntityArrayResponseType = HttpResponse<IAutoevalucionXproceso[]>;

@Injectable({ providedIn: 'root' })
export class AutoevalucionXprocesoService {
  public resourceUrl = SERVER_API_URL + 'api/autoevalucion-xprocesos';

  constructor(protected http: HttpClient) {}

  create(autoevalucionXproceso: IAutoevalucionXproceso): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autoevalucionXproceso);
    return this.http
      .post<IAutoevalucionXproceso>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(autoevalucionXproceso: IAutoevalucionXproceso): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(autoevalucionXproceso);
    return this.http
      .put<IAutoevalucionXproceso>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IAutoevalucionXproceso>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IAutoevalucionXproceso[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(autoevalucionXproceso: IAutoevalucionXproceso): IAutoevalucionXproceso {
    const copy: IAutoevalucionXproceso = Object.assign({}, autoevalucionXproceso, {
      apFechaCreacion:
        autoevalucionXproceso.apFechaCreacion && autoevalucionXproceso.apFechaCreacion.isValid()
          ? autoevalucionXproceso.apFechaCreacion.format(DATE_FORMAT)
          : undefined,
      spFechaModificacion:
        autoevalucionXproceso.spFechaModificacion && autoevalucionXproceso.spFechaModificacion.isValid()
          ? autoevalucionXproceso.spFechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.apFechaCreacion = res.body.apFechaCreacion ? moment(res.body.apFechaCreacion) : undefined;
      res.body.spFechaModificacion = res.body.spFechaModificacion ? moment(res.body.spFechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((autoevalucionXproceso: IAutoevalucionXproceso) => {
        autoevalucionXproceso.apFechaCreacion = autoevalucionXproceso.apFechaCreacion
          ? moment(autoevalucionXproceso.apFechaCreacion)
          : undefined;
        autoevalucionXproceso.spFechaModificacion = autoevalucionXproceso.spFechaModificacion
          ? moment(autoevalucionXproceso.spFechaModificacion)
          : undefined;
      });
    }
    return res;
  }
}
