import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProyectoSharedModule } from 'app/shared/shared.module';
import { MacroprocesoComponent } from './macroproceso.component';
import { MacroprocesoDetailComponent } from './macroproceso-detail.component';
import { MacroprocesoUpdateComponent } from './macroproceso-update.component';
import { MacroprocesoDeleteDialogComponent } from './macroproceso-delete-dialog.component';
import { macroprocesoRoute } from './macroproceso.route';

@NgModule({
  imports: [ProyectoSharedModule, RouterModule.forChild(macroprocesoRoute)],
  declarations: [MacroprocesoComponent, MacroprocesoDetailComponent, MacroprocesoUpdateComponent, MacroprocesoDeleteDialogComponent],
  entryComponents: [MacroprocesoDeleteDialogComponent],
})
export class ProyectoMacroprocesoModule {}
