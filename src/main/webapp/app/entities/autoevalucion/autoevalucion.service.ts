import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAutoevalucion } from 'app/shared/model/autoevalucion.model';

type EntityResponseType = HttpResponse<IAutoevalucion>;
type EntityArrayResponseType = HttpResponse<IAutoevalucion[]>;

@Injectable({ providedIn: 'root' })
export class AutoevalucionService {
  public resourceUrl = SERVER_API_URL + 'api/autoevalucions';

  constructor(protected http: HttpClient) {}

  create(autoevalucion: IAutoevalucion): Observable<EntityResponseType> {
    return this.http.post<IAutoevalucion>(this.resourceUrl, autoevalucion, { observe: 'response' });
  }

  update(autoevalucion: IAutoevalucion): Observable<EntityResponseType> {
    return this.http.put<IAutoevalucion>(this.resourceUrl, autoevalucion, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAutoevalucion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAutoevalucion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
