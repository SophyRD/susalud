import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IUsuarioXEvaluacioXEntidad } from 'app/shared/model/usuario-x-evaluacio-x-entidad.model';

type EntityResponseType = HttpResponse<IUsuarioXEvaluacioXEntidad>;
type EntityArrayResponseType = HttpResponse<IUsuarioXEvaluacioXEntidad[]>;

@Injectable({ providedIn: 'root' })
export class UsuarioXEvaluacioXEntidadService {
  public resourceUrl = SERVER_API_URL + 'api/usuario-x-evaluacio-x-entidads';

  constructor(protected http: HttpClient) {}

  create(usuarioXEvaluacioXEntidad: IUsuarioXEvaluacioXEntidad): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(usuarioXEvaluacioXEntidad);
    return this.http
      .post<IUsuarioXEvaluacioXEntidad>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(usuarioXEvaluacioXEntidad: IUsuarioXEvaluacioXEntidad): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(usuarioXEvaluacioXEntidad);
    return this.http
      .put<IUsuarioXEvaluacioXEntidad>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IUsuarioXEvaluacioXEntidad>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IUsuarioXEvaluacioXEntidad[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(usuarioXEvaluacioXEntidad: IUsuarioXEvaluacioXEntidad): IUsuarioXEvaluacioXEntidad {
    const copy: IUsuarioXEvaluacioXEntidad = Object.assign({}, usuarioXEvaluacioXEntidad, {
      ueeFechaCreacion:
        usuarioXEvaluacioXEntidad.ueeFechaCreacion && usuarioXEvaluacioXEntidad.ueeFechaCreacion.isValid()
          ? usuarioXEvaluacioXEntidad.ueeFechaCreacion.format(DATE_FORMAT)
          : undefined,
      ueeFechaModificacion:
        usuarioXEvaluacioXEntidad.ueeFechaModificacion && usuarioXEvaluacioXEntidad.ueeFechaModificacion.isValid()
          ? usuarioXEvaluacioXEntidad.ueeFechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ueeFechaCreacion = res.body.ueeFechaCreacion ? moment(res.body.ueeFechaCreacion) : undefined;
      res.body.ueeFechaModificacion = res.body.ueeFechaModificacion ? moment(res.body.ueeFechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((usuarioXEvaluacioXEntidad: IUsuarioXEvaluacioXEntidad) => {
        usuarioXEvaluacioXEntidad.ueeFechaCreacion = usuarioXEvaluacioXEntidad.ueeFechaCreacion
          ? moment(usuarioXEvaluacioXEntidad.ueeFechaCreacion)
          : undefined;
        usuarioXEvaluacioXEntidad.ueeFechaModificacion = usuarioXEvaluacioXEntidad.ueeFechaModificacion
          ? moment(usuarioXEvaluacioXEntidad.ueeFechaModificacion)
          : undefined;
      });
    }
    return res;
  }
}
