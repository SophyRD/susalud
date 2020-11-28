import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { UsuariosXevaluacionComponent } from './usuarios-xevaluacion.component';
import { UsuariosXevaluacionDetailComponent } from './usuarios-xevaluacion-detail.component';
import { UsuariosXevaluacionUpdateComponent } from './usuarios-xevaluacion-update.component';
import { UsuariosXevaluacionDeleteDialogComponent } from './usuarios-xevaluacion-delete-dialog.component';
import { usuariosXevaluacionRoute } from './usuarios-xevaluacion.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(usuariosXevaluacionRoute)],
  declarations: [
    UsuariosXevaluacionComponent,
    UsuariosXevaluacionDetailComponent,
    UsuariosXevaluacionUpdateComponent,
    UsuariosXevaluacionDeleteDialogComponent,
  ],
  entryComponents: [UsuariosXevaluacionDeleteDialogComponent],
})
export class SusaludUsuariosXevaluacionModule {}
