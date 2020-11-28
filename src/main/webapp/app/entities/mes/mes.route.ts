import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMes, Mes } from 'app/shared/model/mes.model';
import { MesService } from './mes.service';
import { MesComponent } from './mes.component';
import { MesDetailComponent } from './mes-detail.component';
import { MesUpdateComponent } from './mes-update.component';

@Injectable({ providedIn: 'root' })
export class MesResolve implements Resolve<IMes> {
  constructor(private service: MesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMes> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((mes: HttpResponse<Mes>) => {
          if (mes.body) {
            return of(mes.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Mes());
  }
}

export const mesRoute: Routes = [
  {
    path: '',
    component: MesComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.mes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MesDetailComponent,
    resolve: {
      mes: MesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.mes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MesUpdateComponent,
    resolve: {
      mes: MesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.mes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MesUpdateComponent,
    resolve: {
      mes: MesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.mes.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
