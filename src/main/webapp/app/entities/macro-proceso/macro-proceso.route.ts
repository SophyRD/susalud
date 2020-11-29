import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMacroProceso, MacroProceso } from 'app/shared/model/macro-proceso.model';
import { MacroProcesoService } from './macro-proceso.service';
import { MacroProcesoComponent } from './macro-proceso.component';
import { MacroProcesoDetailComponent } from './macro-proceso-detail.component';
import { MacroProcesoUpdateComponent } from './macro-proceso-update.component';

@Injectable({ providedIn: 'root' })
export class MacroProcesoResolve implements Resolve<IMacroProceso> {
  constructor(private service: MacroProcesoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMacroProceso> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((macroProceso: HttpResponse<MacroProceso>) => {
          if (macroProceso.body) {
            return of(macroProceso.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new MacroProceso());
  }
}

export const macroProcesoRoute: Routes = [
  {
    path: '',
    component: MacroProcesoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MacroProcesos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MacroProcesoDetailComponent,
    resolve: {
      macroProceso: MacroProcesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MacroProcesos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MacroProcesoUpdateComponent,
    resolve: {
      macroProceso: MacroProcesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MacroProcesos',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MacroProcesoUpdateComponent,
    resolve: {
      macroProceso: MacroProcesoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'MacroProcesos',
    },
    canActivate: [UserRouteAccessService],
  },
];
