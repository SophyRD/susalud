import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFuenteReferenciaXItem } from 'app/shared/model/fuente-referencia-x-item.model';

type EntityResponseType = HttpResponse<IFuenteReferenciaXItem>;
type EntityArrayResponseType = HttpResponse<IFuenteReferenciaXItem[]>;

@Injectable({ providedIn: 'root' })
export class FuenteReferenciaXItemService {
  public resourceUrl = SERVER_API_URL + 'api/fuente-referencia-x-items';

  constructor(protected http: HttpClient) {}

  create(fuenteReferenciaXItem: IFuenteReferenciaXItem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fuenteReferenciaXItem);
    return this.http
      .post<IFuenteReferenciaXItem>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fuenteReferenciaXItem: IFuenteReferenciaXItem): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fuenteReferenciaXItem);
    return this.http
      .put<IFuenteReferenciaXItem>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFuenteReferenciaXItem>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFuenteReferenciaXItem[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fuenteReferenciaXItem: IFuenteReferenciaXItem): IFuenteReferenciaXItem {
    const copy: IFuenteReferenciaXItem = Object.assign({}, fuenteReferenciaXItem, {
      friFechaCreacion:
        fuenteReferenciaXItem.friFechaCreacion && fuenteReferenciaXItem.friFechaCreacion.isValid()
          ? fuenteReferenciaXItem.friFechaCreacion.format(DATE_FORMAT)
          : undefined,
      friFechaModificacion:
        fuenteReferenciaXItem.friFechaModificacion && fuenteReferenciaXItem.friFechaModificacion.isValid()
          ? fuenteReferenciaXItem.friFechaModificacion.format(DATE_FORMAT)
          : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.friFechaCreacion = res.body.friFechaCreacion ? moment(res.body.friFechaCreacion) : undefined;
      res.body.friFechaModificacion = res.body.friFechaModificacion ? moment(res.body.friFechaModificacion) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((fuenteReferenciaXItem: IFuenteReferenciaXItem) => {
        fuenteReferenciaXItem.friFechaCreacion = fuenteReferenciaXItem.friFechaCreacion
          ? moment(fuenteReferenciaXItem.friFechaCreacion)
          : undefined;
        fuenteReferenciaXItem.friFechaModificacion = fuenteReferenciaXItem.friFechaModificacion
          ? moment(fuenteReferenciaXItem.friFechaModificacion)
          : undefined;
      });
    }
    return res;
  }
}
