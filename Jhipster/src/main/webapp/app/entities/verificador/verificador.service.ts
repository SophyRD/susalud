import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVerificador } from 'app/shared/model/verificador.model';

type EntityResponseType = HttpResponse<IVerificador>;
type EntityArrayResponseType = HttpResponse<IVerificador[]>;

@Injectable({ providedIn: 'root' })
export class VerificadorService {
  public resourceUrl = SERVER_API_URL + 'api/verificadors';

  constructor(protected http: HttpClient) {}

  create(verificador: IVerificador): Observable<EntityResponseType> {
    return this.http.post<IVerificador>(this.resourceUrl, verificador, { observe: 'response' });
  }

  update(verificador: IVerificador): Observable<EntityResponseType> {
    return this.http.put<IVerificador>(this.resourceUrl, verificador, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IVerificador>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IVerificador[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
