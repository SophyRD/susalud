import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITecnicasEvaluativas } from 'app/shared/model/tecnicas-evaluativas.model';

type EntityResponseType = HttpResponse<ITecnicasEvaluativas>;
type EntityArrayResponseType = HttpResponse<ITecnicasEvaluativas[]>;

@Injectable({ providedIn: 'root' })
export class TecnicasEvaluativasService {
  public resourceUrl = SERVER_API_URL + 'api/tecnicas-evaluativas';

  constructor(protected http: HttpClient) {}

  create(tecnicasEvaluativas: ITecnicasEvaluativas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tecnicasEvaluativas);
    return this.http
      .post<ITecnicasEvaluativas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tecnicasEvaluativas: ITecnicasEvaluativas): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tecnicasEvaluativas);
    return this.http
      .put<ITecnicasEvaluativas>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITecnicasEvaluativas>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITecnicasEvaluativas[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tecnicasEvaluativas: ITecnicasEvaluativas): ITecnicasEvaluativas {
    const copy: ITecnicasEvaluativas = Object.assign({}, tecnicasEvaluativas, {
      teFechaCreacion:
        tecnicasEvaluativas.teFechaCreacion && tecnicasEvaluativas.teFechaCreacion.isValid()
          ? tecnicasEvaluativas.teFechaCreacion.format(DATE_FORMAT)
          : undefined,
      teFechaModificacion:
        tecnicasEvaluativas.teFechaModificacion && tecnicasEvaluativas.teFechaModificacion.isValid()
          ? tecnicasEvaluativas.teFechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.teFechaCreacion = res.body.teFechaCreacion ? moment(res.body.teFechaCreacion) : undefined;
      res.body.teFechaModificacion = res.body.teFechaModificacion ? moment(res.body.teFechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tecnicasEvaluativas: ITecnicasEvaluativas) => {
        tecnicasEvaluativas.teFechaCreacion = tecnicasEvaluativas.teFechaCreacion ? moment(tecnicasEvaluativas.teFechaCreacion) : undefined;
        tecnicasEvaluativas.teFechaModificacion = tecnicasEvaluativas.teFechaModificacion
          ? moment(tecnicasEvaluativas.teFechaModificacion)
          : undefined;
      });
    }
    return res;
  }
}
