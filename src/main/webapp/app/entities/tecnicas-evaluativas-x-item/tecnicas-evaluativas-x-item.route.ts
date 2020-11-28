import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITecnicasEvaluativasXItem, TecnicasEvaluativasXItem } from 'app/shared/model/tecnicas-evaluativas-x-item.model';
import { TecnicasEvaluativasXItemService } from './tecnicas-evaluativas-x-item.service';
import { TecnicasEvaluativasXItemComponent } from './tecnicas-evaluativas-x-item.component';
import { TecnicasEvaluativasXItemDetailComponent } from './tecnicas-evaluativas-x-item-detail.component';
import { TecnicasEvaluativasXItemUpdateComponent } from './tecnicas-evaluativas-x-item-update.component';

@Injectable({ providedIn: 'root' })
export class TecnicasEvaluativasXItemResolve implements Resolve<ITecnicasEvaluativasXItem> {
  constructor(private service: TecnicasEvaluativasXItemService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITecnicasEvaluativasXItem> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tecnicasEvaluativasXItem: HttpResponse<TecnicasEvaluativasXItem>) => {
          if (tecnicasEvaluativasXItem.body) {
            return of(tecnicasEvaluativasXItem.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TecnicasEvaluativasXItem());
  }
}

export const tecnicasEvaluativasXItemRoute: Routes = [
  {
    path: '',
    component: TecnicasEvaluativasXItemComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.tecnicasEvaluativasXItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TecnicasEvaluativasXItemDetailComponent,
    resolve: {
      tecnicasEvaluativasXItem: TecnicasEvaluativasXItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.tecnicasEvaluativasXItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TecnicasEvaluativasXItemUpdateComponent,
    resolve: {
      tecnicasEvaluativasXItem: TecnicasEvaluativasXItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.tecnicasEvaluativasXItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TecnicasEvaluativasXItemUpdateComponent,
    resolve: {
      tecnicasEvaluativasXItem: TecnicasEvaluativasXItemResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.tecnicasEvaluativasXItem.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
