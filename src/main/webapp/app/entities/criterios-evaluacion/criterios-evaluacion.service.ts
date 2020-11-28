import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICriteriosEvaluacion } from 'app/shared/model/criterios-evaluacion.model';

type EntityResponseType = HttpResponse<ICriteriosEvaluacion>;
type EntityArrayResponseType = HttpResponse<ICriteriosEvaluacion[]>;

@Injectable({ providedIn: 'root' })
export class CriteriosEvaluacionService {
  public resourceUrl = SERVER_API_URL + 'api/criterios-evaluacions';

  constructor(protected http: HttpClient) {}

  create(criteriosEvaluacion: ICriteriosEvaluacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(criteriosEvaluacion);
    return this.http
      .post<ICriteriosEvaluacion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(criteriosEvaluacion: ICriteriosEvaluacion): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(criteriosEvaluacion);
    return this.http
      .put<ICriteriosEvaluacion>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICriteriosEvaluacion>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICriteriosEvaluacion[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(criteriosEvaluacion: ICriteriosEvaluacion): ICriteriosEvaluacion {
    const copy: ICriteriosEvaluacion = Object.assign({}, criteriosEvaluacion, {
      ceFechaCreacion:
        criteriosEvaluacion.ceFechaCreacion && criteriosEvaluacion.ceFechaCreacion.isValid()
          ? criteriosEvaluacion.ceFechaCreacion.format(DATE_FORMAT)
          : undefined,
      ceFechaModificacion:
        criteriosEvaluacion.ceFechaModificacion && criteriosEvaluacion.ceFechaModificacion.isValid()
          ? criteriosEvaluacion.ceFechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ceFechaCreacion = res.body.ceFechaCreacion ? moment(res.body.ceFechaCreacion) : undefined;
      res.body.ceFechaModificacion = res.body.ceFechaModificacion ? moment(res.body.ceFechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((criteriosEvaluacion: ICriteriosEvaluacion) => {
        criteriosEvaluacion.ceFechaCreacion = criteriosEvaluacion.ceFechaCreacion ? moment(criteriosEvaluacion.ceFechaCreacion) : undefined;
        criteriosEvaluacion.ceFechaModificacion = criteriosEvaluacion.ceFechaModificacion
          ? moment(criteriosEvaluacion.ceFechaModificacion)
          : undefined;
      });
    }
    return res;
  }
}
