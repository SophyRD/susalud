import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMaestraverificador, Maestraverificador } from 'app/shared/model/maestraverificador.model';
import { MaestraverificadorService } from './maestraverificador.service';
import { MaestraverificadorComponent } from './maestraverificador.component';
import { MaestraverificadorDetailComponent } from './maestraverificador-detail.component';
import { MaestraverificadorUpdateComponent } from './maestraverificador-update.component';

@Injectable({ providedIn: 'root' })
export class MaestraverificadorResolve implements Resolve<IMaestraverificador> {
  constructor(private service: MaestraverificadorService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMaestraverificador> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((maestraverificador: HttpResponse<Maestraverificador>) => {
          if (maestraverificador.body) {
            return of(maestraverificador.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Maestraverificador());
  }
}

export const maestraverificadorRoute: Routes = [
  {
    path: '',
    component: MaestraverificadorComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.maestraverificador.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MaestraverificadorDetailComponent,
    resolve: {
      maestraverificador: MaestraverificadorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.maestraverificador.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MaestraverificadorUpdateComponent,
    resolve: {
      maestraverificador: MaestraverificadorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.maestraverificador.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MaestraverificadorUpdateComponent,
    resolve: {
      maestraverificador: MaestraverificadorResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.maestraverificador.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
