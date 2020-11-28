import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IVerificador, Verificador } from 'app/shared/model/verificador.model';
import { VerificadorService } from './verificador.service';
import { VerificadorComponent } from './verificador.component';
import { VerificadorDetailComponent } from './verificador-detail.component';
import { VerificadorUpdateComponent } from './verificador-update.component';

@Injectable({ providedIn: 'root' })
export class VerificadorResolve implements Resolve<IVerificador> {
  constructor(private service: VerificadorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVerificador> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((verificador: HttpResponse<Verificador>) => {
          if (verificador.body) {
            return of(verificador.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Verificador());
  }
}

export const verificadorRoute: Routes = [
  {
    path: '',
    component: VerificadorComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.verificador.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: VerificadorDetailComponent,
    resolve: {
      verificador: VerificadorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.verificador.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: VerificadorUpdateComponent,
    resolve: {
      verificador: VerificadorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.verificador.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: VerificadorUpdateComponent,
    resolve: {
      verificador: VerificadorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.verificador.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
