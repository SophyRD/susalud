import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITecnicasEvaluativasXItem } from 'app/shared/model/tecnicas-evaluativas-x-item.model';

type EntityResponseType = HttpResponse<ITecnicasEvaluativasXItem>;
type EntityArrayResponseType = HttpResponse<ITecnicasEvaluativasXItem[]>;

@Injectable({ providedIn: 'root' })
export class TecnicasEvaluativasXItemService {
  public resourceUrl = SERVER_API_URL + 'api/tecnicas-evaluativas-x-items';

  constructor(protected http: HttpClient) {}

  create(tecnicasEvaluativasXItem: ITecnicasEvaluativasXItem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tecnicasEvaluativasXItem);
    return this.http
      .post<ITecnicasEvaluativasXItem>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(tecnicasEvaluativasXItem: ITecnicasEvaluativasXItem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(tecnicasEvaluativasXItem);
    return this.http
      .put<ITecnicasEvaluativasXItem>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ITecnicasEvaluativasXItem>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ITecnicasEvaluativasXItem[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(tecnicasEvaluativasXItem: ITecnicasEvaluativasXItem): ITecnicasEvaluativasXItem {
    const copy: ITecnicasEvaluativasXItem = Object.assign({}, tecnicasEvaluativasXItem, {
      tiFechaCreacion:
        tecnicasEvaluativasXItem.tiFechaCreacion && tecnicasEvaluativasXItem.tiFechaCreacion.isValid()
          ? tecnicasEvaluativasXItem.tiFechaCreacion.format(DATE_FORMAT)
          : undefined,
      tiFechaModificacion:
        tecnicasEvaluativasXItem.tiFechaModificacion && tecnicasEvaluativasXItem.tiFechaModificacion.isValid()
          ? tecnicasEvaluativasXItem.tiFechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.tiFechaCreacion = res.body.tiFechaCreacion ? moment(res.body.tiFechaCreacion) : undefined;
      res.body.tiFechaModificacion = res.body.tiFechaModificacion ? moment(res.body.tiFechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((tecnicasEvaluativasXItem: ITecnicasEvaluativasXItem) => {
        tecnicasEvaluativasXItem.tiFechaCreacion = tecnicasEvaluativasXItem.tiFechaCreacion
          ? moment(tecnicasEvaluativasXItem.tiFechaCreacion)
          : undefined;
        tecnicasEvaluativasXItem.tiFechaModificacion = tecnicasEvaluativasXItem.tiFechaModificacion
          ? moment(tecnicasEvaluativasXItem.tiFechaModificacion)
          : undefined;
      });
    }
    return res;
  }
}
