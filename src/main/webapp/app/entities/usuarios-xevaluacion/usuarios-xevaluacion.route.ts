import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IUsuariosXevaluacion, UsuariosXevaluacion } from 'app/shared/model/usuarios-xevaluacion.model';
import { UsuariosXevaluacionService } from './usuarios-xevaluacion.service';
import { UsuariosXevaluacionComponent } from './usuarios-xevaluacion.component';
import { UsuariosXevaluacionDetailComponent } from './usuarios-xevaluacion-detail.component';
import { UsuariosXevaluacionUpdateComponent } from './usuarios-xevaluacion-update.component';

@Injectable({ providedIn: 'root' })
export class UsuariosXevaluacionResolve implements Resolve<IUsuariosXevaluacion> {
  constructor(private service: UsuariosXevaluacionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IUsuariosXevaluacion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((usuariosXevaluacion: HttpResponse<UsuariosXevaluacion>) => {
          if (usuariosXevaluacion.body) {
            return of(usuariosXevaluacion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new UsuariosXevaluacion());
  }
}

export const usuariosXevaluacionRoute: Routes = [
  {
    path: '',
    component: UsuariosXevaluacionComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.usuariosXevaluacion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: UsuariosXevaluacionDetailComponent,
    resolve: {
      usuariosXevaluacion: UsuariosXevaluacionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.usuariosXevaluacion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: UsuariosXevaluacionUpdateComponent,
    resolve: {
      usuariosXevaluacion: UsuariosXevaluacionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.usuariosXevaluacion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: UsuariosXevaluacionUpdateComponent,
    resolve: {
      usuariosXevaluacion: UsuariosXevaluacionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'susaludApp.usuariosXevaluacion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
