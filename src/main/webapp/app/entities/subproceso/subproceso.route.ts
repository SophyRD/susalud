import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubproceso, Subproceso } from 'app/shared/model/subproceso.model';
import { SubprocesoService } from './subproceso.service';
import { SubprocesoComponent } from './subproceso.component';
import { SubprocesoDetailComponent } from './subproceso-detail.component';
import { SubprocesoUpdateComponent } from './subproceso-update.component';

@Injectable({ providedIn: 'root' })
export class SubprocesoResolve implements Resolve<ISubproceso> {
  constructor(private service: SubprocesoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubproceso> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subproceso: HttpResponse<Subproceso>) => {
          if (subproceso.body) {
            return of(subproceso.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Subproceso());
  }
}

export const subprocesoRoute: Routes = [
  {
    path: '',
    component: SubprocesoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.subproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SubprocesoDetailComponent,
    resolve: {
      subproceso: SubprocesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.subproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SubprocesoUpdateComponent,
    resolve: {
      subproceso: SubprocesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.subproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SubprocesoUpdateComponent,
    resolve: {
      subproceso: SubprocesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.subproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
