import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVerificador } from 'app/shared/model/verificador.model';

type EntityResponseType = HttpResponse<IVerificador>;
type EntityArrayResponseType = HttpResponse<IVerificador[]>;

@Injectable({ providedIn: 'root' })
export class VerificadorService {
  public resourceUrl = SERVER_API_URL + 'api/verificadors';

  constructor(protected http: HttpClient) {}

  create(verificador: IVerificador): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(verificador);
    return this.http
      .post<IVerificador>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(verificador: IVerificador): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(verificador);
    return this.http
      .put<IVerificador>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVerificador>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVerificador[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(verificador: IVerificador): IVerificador {
    const copy: IVerificador = Object.assign({}, verificador, {
      v1FechaCreacion:
        verificador.v1FechaCreacion && verificador.v1FechaCreacion.isValid() ? verificador.v1FechaCreacion.format(DATE_FORMAT) : undefined,
      v1FechaModificacion:
        verificador.v1FechaModificacion && verificador.v1FechaModificacion.isValid()
          ? verificador.v1FechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.v1FechaCreacion = res.body.v1FechaCreacion ? moment(res.body.v1FechaCreacion) : undefined;
      res.body.v1FechaModificacion = res.body.v1FechaModificacion ? moment(res.body.v1FechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((verificador: IVerificador) => {
        verificador.v1FechaCreacion = verificador.v1FechaCreacion ? moment(verificador.v1FechaCreacion) : undefined;
        verificador.v1FechaModificacion = verificador.v1FechaModificacion ? moment(verificador.v1FechaModificacion) : undefined;
      });
    }
    return res;
  }
}
