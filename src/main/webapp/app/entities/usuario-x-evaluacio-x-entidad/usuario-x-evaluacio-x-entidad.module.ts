import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { UsuarioXEvaluacioXEntidadComponent } from './usuario-x-evaluacio-x-entidad.component';
import { UsuarioXEvaluacioXEntidadDetailComponent } from './usuario-x-evaluacio-x-entidad-detail.component';
import { UsuarioXEvaluacioXEntidadUpdateComponent } from './usuario-x-evaluacio-x-entidad-update.component';
import { UsuarioXEvaluacioXEntidadDeleteDialogComponent } from './usuario-x-evaluacio-x-entidad-delete-dialog.component';
import { usuarioXEvaluacioXEntidadRoute } from './usuario-x-evaluacio-x-entidad.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(usuarioXEvaluacioXEntidadRoute)],
  declarations: [
    UsuarioXEvaluacioXEntidadComponent,
    UsuarioXEvaluacioXEntidadDetailComponent,
    UsuarioXEvaluacioXEntidadUpdateComponent,
    UsuarioXEvaluacioXEntidadDeleteDialogComponent,
  ],
  entryComponents: [UsuarioXEvaluacioXEntidadDeleteDialogComponent],
})
export class SusaludUsuarioXEvaluacioXEntidadModule {}
