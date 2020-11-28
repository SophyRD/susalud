import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPeriodo, Periodo } from 'app/shared/model/periodo.model';
import { PeriodoService } from './periodo.service';
import { PeriodoComponent } from './periodo.component';
import { PeriodoDetailComponent } from './periodo-detail.component';
import { PeriodoUpdateComponent } from './periodo-update.component';

@Injectable({ providedIn: 'root' })
export class PeriodoResolve implements Resolve<IPeriodo> {
  constructor(private service: PeriodoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPeriodo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((periodo: HttpResponse<Periodo>) => {
          if (periodo.body) {
            return of(periodo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Periodo());
  }
}

export const periodoRoute: Routes = [
  {
    path: '',
    component: PeriodoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.periodo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PeriodoDetailComponent,
    resolve: {
      periodo: PeriodoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.periodo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PeriodoUpdateComponent,
    resolve: {
      periodo: PeriodoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.periodo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PeriodoUpdateComponent,
    resolve: {
      periodo: PeriodoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.periodo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
