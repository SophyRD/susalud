import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMaestraverificador } from 'app/shared/model/maestraverificador.model';

type EntityResponseType = HttpResponse<IMaestraverificador>;
type EntityArrayResponseType = HttpResponse<IMaestraverificador[]>;

@Injectable({ providedIn: 'root' })
export class MaestraverificadorService {
  public resourceUrl = SERVER_API_URL + 'api/maestraverificadors';

  constructor(protected http: HttpClient) {}

  create(maestraverificador: IMaestraverificador): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(maestraverificador);
    return this.http
      .post<IMaestraverificador>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(maestraverificador: IMaestraverificador): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(maestraverificador);
    return this.http
      .put<IMaestraverificador>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IMaestraverificador>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IMaestraverificador[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(maestraverificador: IMaestraverificador): IMaestraverificador {
    const copy: IMaestraverificador = Object.assign({}, maestraverificador, {
      mvFecha:
        maestraverificador.mvFecha && maestraverificador.mvFecha.isValid() ? maestraverificador.mvFecha.format(DATE_FORMAT) : undefined,
      mvFechaModificacion:
        maestraverificador.mvFechaModificacion && maestraverificador.mvFechaModificacion.isValid()
          ? maestraverificador.mvFechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.mvFecha = res.body.mvFecha ? moment(res.body.mvFecha) : undefined;
      res.body.mvFechaModificacion = res.body.mvFechaModificacion ? moment(res.body.mvFechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((maestraverificador: IMaestraverificador) => {
        maestraverificador.mvFecha = maestraverificador.mvFecha ? moment(maestraverificador.mvFecha) : undefined;
        maestraverificador.mvFechaModificacion = maestraverificador.mvFechaModificacion
          ? moment(maestraverificador.mvFechaModificacion)
          : undefined;
      });
    }
    return res;
  }
}
