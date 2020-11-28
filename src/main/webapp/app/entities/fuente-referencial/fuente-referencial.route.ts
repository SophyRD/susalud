import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFuenteReferencial, FuenteReferencial } from 'app/shared/model/fuente-referencial.model';
import { FuenteReferencialService } from './fuente-referencial.service';
import { FuenteReferencialComponent } from './fuente-referencial.component';
import { FuenteReferencialDetailComponent } from './fuente-referencial-detail.component';
import { FuenteReferencialUpdateComponent } from './fuente-referencial-update.component';

@Injectable({ providedIn: 'root' })
export class FuenteReferencialResolve implements Resolve<IFuenteReferencial> {
  constructor(private service: FuenteReferencialService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFuenteReferencial> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fuenteReferencial: HttpResponse<FuenteReferencial>) => {
          if (fuenteReferencial.body) {
            return of(fuenteReferencial.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FuenteReferencial());
  }
}

export const fuenteReferencialRoute: Routes = [
  {
    path: '',
    component: FuenteReferencialComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.fuenteReferencial.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FuenteReferencialDetailComponent,
    resolve: {
      fuenteReferencial: FuenteReferencialResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.fuenteReferencial.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FuenteReferencialUpdateComponent,
    resolve: {
      fuenteReferencial: FuenteReferencialResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.fuenteReferencial.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FuenteReferencialUpdateComponent,
    resolve: {
      fuenteReferencial: FuenteReferencialResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.fuenteReferencial.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
