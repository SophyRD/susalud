import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAutoevalucion, Autoevalucion } from 'app/shared/model/autoevalucion.model';
import { AutoevalucionService } from './autoevalucion.service';
import { AutoevalucionComponent } from './autoevalucion.component';
import { AutoevalucionDetailComponent } from './autoevalucion-detail.component';
import { AutoevalucionUpdateComponent } from './autoevalucion-update.component';

@Injectable({ providedIn: 'root' })
export class AutoevalucionResolve implements Resolve<IAutoevalucion> {
  constructor(private service: AutoevalucionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAutoevalucion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((autoevalucion: HttpResponse<Autoevalucion>) => {
          if (autoevalucion.body) {
            return of(autoevalucion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Autoevalucion());
  }
}

export const autoevalucionRoute: Routes = [
  {
    path: '',
    component: AutoevalucionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.autoevalucion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AutoevalucionDetailComponent,
    resolve: {
      autoevalucion: AutoevalucionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.autoevalucion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AutoevalucionUpdateComponent,
    resolve: {
      autoevalucion: AutoevalucionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.autoevalucion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AutoevalucionUpdateComponent,
    resolve: {
      autoevalucion: AutoevalucionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.autoevalucion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
