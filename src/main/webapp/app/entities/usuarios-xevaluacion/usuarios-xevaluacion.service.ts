import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUsuariosXevaluacion } from 'app/shared/model/usuarios-xevaluacion.model';

type EntityResponseType = HttpResponse<IUsuariosXevaluacion>;
type EntityArrayResponseType = HttpResponse<IUsuariosXevaluacion[]>;

@Injectable({ providedIn: 'root' })
export class UsuariosXevaluacionService {
  public resourceUrl = SERVER_API_URL + 'api/usuarios-xevaluacions';

  constructor(protected http: HttpClient) {}

  create(usuariosXevaluacion: IUsuariosXevaluacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(usuariosXevaluacion);
    return this.http
      .post<IUsuariosXevaluacion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(usuariosXevaluacion: IUsuariosXevaluacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(usuariosXevaluacion);
    return this.http
      .put<IUsuariosXevaluacion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUsuariosXevaluacion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUsuariosXevaluacion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(usuariosXevaluacion: IUsuariosXevaluacion): IUsuariosXevaluacion {
    const copy: IUsuariosXevaluacion = Object.assign({}, usuariosXevaluacion, {
      uFechaModificacion:
        usuariosXevaluacion.uFechaModificacion && usuariosXevaluacion.uFechaModificacion.isValid()
          ? usuariosXevaluacion.uFechaModificacion.format(DATE_FORMAT)
          : undefined,
      uFechaCreacion:
        usuariosXevaluacion.uFechaCreacion && usuariosXevaluacion.uFechaCreacion.isValid()
          ? usuariosXevaluacion.uFechaCreacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.uFechaModificacion = res.body.uFechaModificacion ? moment(res.body.uFechaModificacion) : undefined;
      res.body.uFechaCreacion = res.body.uFechaCreacion ? moment(res.body.uFechaCreacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((usuariosXevaluacion: IUsuariosXevaluacion) => {
        usuariosXevaluacion.uFechaModificacion = usuariosXevaluacion.uFechaModificacion
          ? moment(usuariosXevaluacion.uFechaModificacion)
          : undefined;
        usuariosXevaluacion.uFechaCreacion = usuariosXevaluacion.uFechaCreacion ? moment(usuariosXevaluacion.uFechaCreacion) : undefined;
      });
    }
    return res;
  }
}
