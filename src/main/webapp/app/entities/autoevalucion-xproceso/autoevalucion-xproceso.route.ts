import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAutoevalucionXproceso, AutoevalucionXproceso } from 'app/shared/model/autoevalucion-xproceso.model';
import { AutoevalucionXprocesoService } from './autoevalucion-xproceso.service';
import { AutoevalucionXprocesoComponent } from './autoevalucion-xproceso.component';
import { AutoevalucionXprocesoDetailComponent } from './autoevalucion-xproceso-detail.component';
import { AutoevalucionXprocesoUpdateComponent } from './autoevalucion-xproceso-update.component';

@Injectable({ providedIn: 'root' })
export class AutoevalucionXprocesoResolve implements Resolve<IAutoevalucionXproceso> {
  constructor(private service: AutoevalucionXprocesoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAutoevalucionXproceso> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((autoevalucionXproceso: HttpResponse<AutoevalucionXproceso>) => {
          if (autoevalucionXproceso.body) {
            return of(autoevalucionXproceso.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AutoevalucionXproceso());
  }
}

export const autoevalucionXprocesoRoute: Routes = [
  {
    path: '',
    component: AutoevalucionXprocesoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.autoevalucionXproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutoevalucionXprocesoDetailComponent,
    resolve: {
      autoevalucionXproceso: AutoevalucionXprocesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.autoevalucionXproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutoevalucionXprocesoUpdateComponent,
    resolve: {
      autoevalucionXproceso: AutoevalucionXprocesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.autoevalucionXproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutoevalucionXprocesoUpdateComponent,
    resolve: {
      autoevalucionXproceso: AutoevalucionXprocesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.autoevalucionXproceso.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
