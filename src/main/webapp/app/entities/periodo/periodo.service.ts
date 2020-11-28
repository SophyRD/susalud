import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPeriodo } from 'app/shared/model/periodo.model';

type EntityResponseType = HttpResponse<IPeriodo>;
type EntityArrayResponseType = HttpResponse<IPeriodo[]>;

@Injectable({ providedIn: 'root' })
export class PeriodoService {
  public resourceUrl = SERVER_API_URL + 'api/periodos';

  constructor(protected http: HttpClient) {}

  create(periodo: IPeriodo): Observable<EntityResponseType> {
    return this.http.post<IPeriodo>(this.resourceUrl, periodo, { observe: 'response' });
  }

  update(periodo: IPeriodo): Observable<EntityResponseType> {
    return this.http.put<IPeriodo>(this.resourceUrl, periodo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPeriodo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPeriodo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
