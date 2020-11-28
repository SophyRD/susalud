import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITecnicasEvaluativas, TecnicasEvaluativas } from 'app/shared/model/tecnicas-evaluativas.model';
import { TecnicasEvaluativasService } from './tecnicas-evaluativas.service';
import { TecnicasEvaluativasComponent } from './tecnicas-evaluativas.component';
import { TecnicasEvaluativasDetailComponent } from './tecnicas-evaluativas-detail.component';
import { TecnicasEvaluativasUpdateComponent } from './tecnicas-evaluativas-update.component';

@Injectable({ providedIn: 'root' })
export class TecnicasEvaluativasResolve implements Resolve<ITecnicasEvaluativas> {
  constructor(private service: TecnicasEvaluativasService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITecnicasEvaluativas> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tecnicasEvaluativas: HttpResponse<TecnicasEvaluativas>) => {
          if (tecnicasEvaluativas.body) {
            return of(tecnicasEvaluativas.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TecnicasEvaluativas());
  }
}

export const tecnicasEvaluativasRoute: Routes = [
  {
    path: '',
    component: TecnicasEvaluativasComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.tecnicasEvaluativas.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TecnicasEvaluativasDetailComponent,
    resolve: {
      tecnicasEvaluativas: TecnicasEvaluativasResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.tecnicasEvaluativas.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TecnicasEvaluativasUpdateComponent,
    resolve: {
      tecnicasEvaluativas: TecnicasEvaluativasResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.tecnicasEvaluativas.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TecnicasEvaluativasUpdateComponent,
    resolve: {
      tecnicasEvaluativas: TecnicasEvaluativasResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.tecnicasEvaluativas.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
