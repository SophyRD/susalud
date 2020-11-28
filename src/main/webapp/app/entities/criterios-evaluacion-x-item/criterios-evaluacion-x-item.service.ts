import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICriteriosEvaluacionXItem } from 'app/shared/model/criterios-evaluacion-x-item.model';

type EntityResponseType = HttpResponse<ICriteriosEvaluacionXItem>;
type EntityArrayResponseType = HttpResponse<ICriteriosEvaluacionXItem[]>;

@Injectable({ providedIn: 'root' })
export class CriteriosEvaluacionXItemService {
  public resourceUrl = SERVER_API_URL + 'api/criterios-evaluacion-x-items';

  constructor(protected http: HttpClient) {}

  create(criteriosEvaluacionXItem: ICriteriosEvaluacionXItem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(criteriosEvaluacionXItem);
    return this.http
      .post<ICriteriosEvaluacionXItem>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(criteriosEvaluacionXItem: ICriteriosEvaluacionXItem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(criteriosEvaluacionXItem);
    return this.http
      .put<ICriteriosEvaluacionXItem>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<ICriteriosEvaluacionXItem>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<ICriteriosEvaluacionXItem[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(criteriosEvaluacionXItem: ICriteriosEvaluacionXItem): ICriteriosEvaluacionXItem {
    const copy: ICriteriosEvaluacionXItem = Object.assign({}, criteriosEvaluacionXItem, {
      ceiFechaCreacion:
        criteriosEvaluacionXItem.ceiFechaCreacion && criteriosEvaluacionXItem.ceiFechaCreacion.isValid()
          ? criteriosEvaluacionXItem.ceiFechaCreacion.format(DATE_FORMAT)
          : undefined,
      ceiFechaModificacion:
        criteriosEvaluacionXItem.ceiFechaModificacion && criteriosEvaluacionXItem.ceiFechaModificacion.isValid()
          ? criteriosEvaluacionXItem.ceiFechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.ceiFechaCreacion = res.body.ceiFechaCreacion ? moment(res.body.ceiFechaCreacion) : undefined;
      res.body.ceiFechaModificacion = res.body.ceiFechaModificacion ? moment(res.body.ceiFechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((criteriosEvaluacionXItem: ICriteriosEvaluacionXItem) => {
        criteriosEvaluacionXItem.ceiFechaCreacion = criteriosEvaluacionXItem.ceiFechaCreacion
          ? moment(criteriosEvaluacionXItem.ceiFechaCreacion)
          : undefined;
        criteriosEvaluacionXItem.ceiFechaModificacion = criteriosEvaluacionXItem.ceiFechaModificacion
          ? moment(criteriosEvaluacionXItem.ceiFechaModificacion)
          : undefined;
      });
    }
    return res;
  }
}
