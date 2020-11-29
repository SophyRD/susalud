import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISubProceso } from 'app/shared/model/sub-proceso.model';

type EntityResponseType = HttpResponse<ISubProceso>;
type EntityArrayResponseType = HttpResponse<ISubProceso[]>;

@Injectable({ providedIn: 'root' })
export class SubProcesoService {
  public resourceUrl = SERVER_API_URL + 'api/sub-procesos';

  constructor(protected http: HttpClient) {}

  create(subProceso: ISubProceso): Observable<EntityResponseType> {
    return this.http.post<ISubProceso>(this.resourceUrl, subProceso, { observe: 'response' });
  }

  update(subProceso: ISubProceso): Observable<EntityResponseType> {
    return this.http.put<ISubProceso>(this.resourceUrl, subProceso, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISubProceso>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISubProceso[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
