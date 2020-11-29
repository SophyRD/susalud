import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'proceso',
        loadChildren: () => import('./proceso/proceso.module').then(m => m.SusaludProcesoModule),
      },
      {
        path: 'sub-proceso',
        loadChildren: () => import('./sub-proceso/sub-proceso.module').then(m => m.SusaludSubProcesoModule),
      },
      {
        path: 'macro-proceso',
        loadChildren: () => import('./macro-proceso/macro-proceso.module').then(m => m.SusaludMacroProcesoModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SusaludEntityModule {}
