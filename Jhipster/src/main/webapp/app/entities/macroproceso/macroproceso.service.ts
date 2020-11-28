import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMacroproceso } from 'app/shared/model/macroproceso.model';

type EntityResponseType = HttpResponse<IMacroproceso>;
type EntityArrayResponseType = HttpResponse<IMacroproceso[]>;

@Injectable({ providedIn: 'root' })
export class MacroprocesoService {
  public resourceUrl = SERVER_API_URL + 'api/macroprocesos';

  constructor(protected http: HttpClient) {}

  create(macroproceso: IMacroproceso): Observable<EntityResponseType> {
    return this.http.post<IMacroproceso>(this.resourceUrl, macroproceso, { observe: 'response' });
  }

  update(macroproceso: IMacroproceso): Observable<EntityResponseType> {
    return this.http.put<IMacroproceso>(this.resourceUrl, macroproceso, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMacroproceso>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMacroproceso[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
