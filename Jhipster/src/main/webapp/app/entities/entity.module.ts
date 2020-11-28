import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'macroproceso',
        loadChildren: () => import('./macroproceso/macroproceso.module').then(m => m.ProyectoMacroprocesoModule),
      },
      {
        path: 'proceso',
        loadChildren: () => import('./proceso/proceso.module').then(m => m.ProyectoProcesoModule),
      },
      {
        path: 'subproceso',
        loadChildren: () => import('./subproceso/subproceso.module').then(m => m.ProyectoSubprocesoModule),
      },
      {
        path: 'verificador',
        loadChildren: () => import('./verificador/verificador.module').then(m => m.ProyectoVerificadorModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ProyectoEntityModule {}
