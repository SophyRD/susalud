import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { EstadoComponent } from './estado.component';
import { EstadoDetailComponent } from './estado-detail.component';
import { EstadoUpdateComponent } from './estado-update.component';
import { EstadoDeleteDialogComponent } from './estado-delete-dialog.component';
import { estadoRoute } from './estado.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(estadoRoute)],
  declarations: [EstadoComponent, EstadoDetailComponent, EstadoUpdateComponent, EstadoDeleteDialogComponent],
  entryComponents: [EstadoDeleteDialogComponent],
})
export class SusaludEstadoModule {}
