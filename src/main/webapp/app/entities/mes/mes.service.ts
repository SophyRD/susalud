import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMes } from 'app/shared/model/mes.model';

type EntityResponseType = HttpResponse<IMes>;
type EntityArrayResponseType = HttpResponse<IMes[]>;

@Injectable({ providedIn: 'root' })
export class MesService {
  public resourceUrl = SERVER_API_URL + 'api/mes';

  constructor(protected http: HttpClient) {}

  create(mes: IMes): Observable<EntityResponseType> {
    return this.http.post<IMes>(this.resourceUrl, mes, { observe: 'response' });
  }

  update(mes: IMes): Observable<EntityResponseType> {
    return this.http.put<IMes>(this.resourceUrl, mes, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMes>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMes[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
