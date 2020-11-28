import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { FuenteReferencialComponent } from './fuente-referencial.component';
import { FuenteReferencialDetailComponent } from './fuente-referencial-detail.component';
import { FuenteReferencialUpdateComponent } from './fuente-referencial-update.component';
import { FuenteReferencialDeleteDialogComponent } from './fuente-referencial-delete-dialog.component';
import { fuenteReferencialRoute } from './fuente-referencial.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(fuenteReferencialRoute)],
  declarations: [
    FuenteReferencialComponent,
    FuenteReferencialDetailComponent,
    FuenteReferencialUpdateComponent,
    FuenteReferencialDeleteDialogComponent,
  ],
  entryComponents: [FuenteReferencialDeleteDialogComponent],
})
export class SusaludFuenteReferencialModule {}
