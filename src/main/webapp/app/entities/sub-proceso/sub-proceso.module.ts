import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { SubProcesoComponent } from './sub-proceso.component';
import { SubProcesoDetailComponent } from './sub-proceso-detail.component';
import { SubProcesoUpdateComponent } from './sub-proceso-update.component';
import { SubProcesoDeleteDialogComponent } from './sub-proceso-delete-dialog.component';
import { subProcesoRoute } from './sub-proceso.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(subProcesoRoute)],
  declarations: [SubProcesoComponent, SubProcesoDetailComponent, SubProcesoUpdateComponent, SubProcesoDeleteDialogComponent],
  entryComponents: [SubProcesoDeleteDialogComponent],
})
export class SusaludSubProcesoModule {}
