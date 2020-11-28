import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMacroproceso, Macroproceso } from 'app/shared/model/macroproceso.model';
import { MacroprocesoService } from './macroproceso.service';
import { MacroprocesoComponent } from './macroproceso.component';
import { MacroprocesoDetailComponent } from './macroproceso-detail.component';
import { MacroprocesoUpdateComponent } from './macroproceso-update.component';

@Injectable({ providedIn: 'root' })
export class MacroprocesoResolve implements Resolve<IMacroproceso> {
  constructor(private service: MacroprocesoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMacroproceso> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((macroproceso: HttpResponse<Macroproceso>) => {
          if (macroproceso.body) {
            return of(macroproceso.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Macroproceso());
  }
}

export const macroprocesoRoute: Routes = [
  {
    path: '',
    component: MacroprocesoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.macroproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MacroprocesoDetailComponent,
    resolve: {
      macroproceso: MacroprocesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.macroproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MacroprocesoUpdateComponent,
    resolve: {
      macroproceso: MacroprocesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.macroproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MacroprocesoUpdateComponent,
    resolve: {
      macroproceso: MacroprocesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.macroproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
