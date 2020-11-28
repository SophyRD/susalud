import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFuenteReferenciaXItem, FuenteReferenciaXItem } from 'app/shared/model/fuente-referencia-x-item.model';
import { FuenteReferenciaXItemService } from './fuente-referencia-x-item.service';
import { FuenteReferenciaXItemComponent } from './fuente-referencia-x-item.component';
import { FuenteReferenciaXItemDetailComponent } from './fuente-referencia-x-item-detail.component';
import { FuenteReferenciaXItemUpdateComponent } from './fuente-referencia-x-item-update.component';

@Injectable({ providedIn: 'root' })
export class FuenteReferenciaXItemResolve implements Resolve<IFuenteReferenciaXItem> {
  constructor(private service: FuenteReferenciaXItemService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFuenteReferenciaXItem> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fuenteReferenciaXItem: HttpResponse<FuenteReferenciaXItem>) => {
          if (fuenteReferenciaXItem.body) {
            return of(fuenteReferenciaXItem.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FuenteReferenciaXItem());
  }
}

export const fuenteReferenciaXItemRoute: Routes = [
  {
    path: '',
    component: FuenteReferenciaXItemComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.fuenteReferenciaXItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: FuenteReferenciaXItemDetailComponent,
    resolve: {
      fuenteReferenciaXItem: FuenteReferenciaXItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.fuenteReferenciaXItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: FuenteReferenciaXItemUpdateComponent,
    resolve: {
      fuenteReferenciaXItem: FuenteReferenciaXItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.fuenteReferenciaXItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: FuenteReferenciaXItemUpdateComponent,
    resolve: {
      fuenteReferenciaXItem: FuenteReferenciaXItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.fuenteReferenciaXItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
