import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISubProceso, SubProceso } from 'app/shared/model/sub-proceso.model';
import { SubProcesoService } from './sub-proceso.service';
import { SubProcesoComponent } from './sub-proceso.component';
import { SubProcesoDetailComponent } from './sub-proceso-detail.component';
import { SubProcesoUpdateComponent } from './sub-proceso-update.component';

@Injectable({ providedIn: 'root' })
export class SubProcesoResolve implements Resolve<ISubProceso> {
  constructor(private service: SubProcesoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISubProceso> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((subProceso: HttpResponse<SubProceso>) => {
          if (subProceso.body) {
            return of(subProceso.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SubProceso());
  }
}

export const subProcesoRoute: Routes = [
  {
    path: '',
    component: SubProcesoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SubProcesos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SubProcesoDetailComponent,
    resolve: {
      subProceso: SubProcesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SubProcesos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SubProcesoUpdateComponent,
    resolve: {
      subProceso: SubProcesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SubProcesos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SubProcesoUpdateComponent,
    resolve: {
      subProceso: SubProcesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SubProcesos',
    },
    canActivate: [UserRouteAccessService],
  },
];
