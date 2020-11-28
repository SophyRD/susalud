import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProyectoSharedModule } from 'app/shared/shared.module';
import { SubprocesoComponent } from './subproceso.component';
import { SubprocesoDetailComponent } from './subproceso-detail.component';
import { SubprocesoUpdateComponent } from './subproceso-update.component';
import { SubprocesoDeleteDialogComponent } from './subproceso-delete-dialog.component';
import { subprocesoRoute } from './subproceso.route';

@NgModule({
  imports: [ProyectoSharedModule, RouterModule.forChild(subprocesoRoute)],
  declarations: [SubprocesoComponent, SubprocesoDetailComponent, SubprocesoUpdateComponent, SubprocesoDeleteDialogComponent],
  entryComponents: [SubprocesoDeleteDialogComponent],
})
export class ProyectoSubprocesoModule {}
