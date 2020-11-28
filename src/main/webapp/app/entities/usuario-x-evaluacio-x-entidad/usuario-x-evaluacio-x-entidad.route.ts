import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUsuarioXEvaluacioXEntidad, UsuarioXEvaluacioXEntidad } from 'app/shared/model/usuario-x-evaluacio-x-entidad.model';
import { UsuarioXEvaluacioXEntidadService } from './usuario-x-evaluacio-x-entidad.service';
import { UsuarioXEvaluacioXEntidadComponent } from './usuario-x-evaluacio-x-entidad.component';
import { UsuarioXEvaluacioXEntidadDetailComponent } from './usuario-x-evaluacio-x-entidad-detail.component';
import { UsuarioXEvaluacioXEntidadUpdateComponent } from './usuario-x-evaluacio-x-entidad-update.component';

@Injectable({ providedIn: 'root' })
export class UsuarioXEvaluacioXEntidadResolve implements Resolve<IUsuarioXEvaluacioXEntidad> {
  constructor(private service: UsuarioXEvaluacioXEntidadService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUsuarioXEvaluacioXEntidad> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((usuarioXEvaluacioXEntidad: HttpResponse<UsuarioXEvaluacioXEntidad>) => {
          if (usuarioXEvaluacioXEntidad.body) {
            return of(usuarioXEvaluacioXEntidad.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UsuarioXEvaluacioXEntidad());
  }
}

export const usuarioXEvaluacioXEntidadRoute: Routes = [
  {
    path: '',
    component: UsuarioXEvaluacioXEntidadComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.usuarioXEvaluacioXEntidad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UsuarioXEvaluacioXEntidadDetailComponent,
    resolve: {
      usuarioXEvaluacioXEntidad: UsuarioXEvaluacioXEntidadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.usuarioXEvaluacioXEntidad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UsuarioXEvaluacioXEntidadUpdateComponent,
    resolve: {
      usuarioXEvaluacioXEntidad: UsuarioXEvaluacioXEntidadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.usuarioXEvaluacioXEntidad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UsuarioXEvaluacioXEntidadUpdateComponent,
    resolve: {
      usuarioXEvaluacioXEntidad: UsuarioXEvaluacioXEntidadResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.usuarioXEvaluacioXEntidad.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
