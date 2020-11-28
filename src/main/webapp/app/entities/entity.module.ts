import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'verificador',
        loadChildren: () => import('./verificador/verificador.module').then(m => m.SusaludVerificadorModule),
      },
      {
        path: 'maestraverificador',
        loadChildren: () => import('./maestraverificador/maestraverificador.module').then(m => m.SusaludMaestraverificadorModule),
      },
      {
        path: 'mes',
        loadChildren: () => import('./mes/mes.module').then(m => m.SusaludMesModule),
      },
      {
        path: 'periodo',
        loadChildren: () => import('./periodo/periodo.module').then(m => m.SusaludPeriodoModule),
      },
      {
        path: 'usuarios-xevaluacion',
        loadChildren: () => import('./usuarios-xevaluacion/usuarios-xevaluacion.module').then(m => m.SusaludUsuariosXevaluacionModule),
      },
      {
        path: 'perfil',
        loadChildren: () => import('./perfil/perfil.module').then(m => m.SusaludPerfilModule),
      },
      {
        path: 'estado',
        loadChildren: () => import('./estado/estado.module').then(m => m.SusaludEstadoModule),
      },
      {
        path: 'autoevalucion',
        loadChildren: () => import('./autoevalucion/autoevalucion.module').then(m => m.SusaludAutoevalucionModule),
      },
      {
        path: 'usuarios',
        loadChildren: () => import('./usuarios/usuarios.module').then(m => m.SusaludUsuariosModule),
      },
      {
        path: 'autoevalucion-xproceso',
        loadChildren: () =>
          import('./autoevalucion-xproceso/autoevalucion-xproceso.module').then(m => m.SusaludAutoevalucionXprocesoModule),
      },
      {
        path: 'tecnicas-evaluativas-x-item',
        loadChildren: () =>
          import('./tecnicas-evaluativas-x-item/tecnicas-evaluativas-x-item.module').then(m => m.SusaludTecnicasEvaluativasXItemModule),
      },
      {
        path: 'tecnicas-evaluativas',
        loadChildren: () => import('./tecnicas-evaluativas/tecnicas-evaluativas.module').then(m => m.SusaludTecnicasEvaluativasModule),
      },
      {
        path: 'macroproceso',
        loadChildren: () => import('./macroproceso/macroproceso.module').then(m => m.SusaludMacroprocesoModule),
      },
      {
        path: 'proceso',
        loadChildren: () => import('./proceso/proceso.module').then(m => m.SusaludProcesoModule),
      },
      {
        path: 'subproceso',
        loadChildren: () => import('./subproceso/subproceso.module').then(m => m.SusaludSubprocesoModule),
      },
      {
        path: 'fuente-referencia-x-item',
        loadChildren: () =>
          import('./fuente-referencia-x-item/fuente-referencia-x-item.module').then(m => m.SusaludFuenteReferenciaXItemModule),
      },
      {
        path: 'fuente-referencial',
        loadChildren: () => import('./fuente-referencial/fuente-referencial.module').then(m => m.SusaludFuenteReferencialModule),
      },
      {
        path: 'criterios-evaluacion-x-item',
        loadChildren: () =>
          import('./criterios-evaluacion-x-item/criterios-evaluacion-x-item.module').then(m => m.SusaludCriteriosEvaluacionXItemModule),
      },
      {
        path: 'criterios-evaluacion',
        loadChildren: () => import('./criterios-evaluacion/criterios-evaluacion.module').then(m => m.SusaludCriteriosEvaluacionModule),
      },
      {
        path: 'usuario-x-evaluacio-x-entidad',
        loadChildren: () =>
          import('./usuario-x-evaluacio-x-entidad/usuario-x-evaluacio-x-entidad.module').then(
            m => m.SusaludUsuarioXEvaluacioXEntidadModule
          ),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SusaludEntityModule {}
