import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { FuenteReferenciaXItemComponent } from './fuente-referencia-x-item.component';
import { FuenteReferenciaXItemDetailComponent } from './fuente-referencia-x-item-detail.component';
import { FuenteReferenciaXItemUpdateComponent } from './fuente-referencia-x-item-update.component';
import { FuenteReferenciaXItemDeleteDialogComponent } from './fuente-referencia-x-item-delete-dialog.component';
import { fuenteReferenciaXItemRoute } from './fuente-referencia-x-item.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(fuenteReferenciaXItemRoute)],
  declarations: [
    FuenteReferenciaXItemComponent,
    FuenteReferenciaXItemDetailComponent,
    FuenteReferenciaXItemUpdateComponent,
    FuenteReferenciaXItemDeleteDialogComponent,
  ],
  entryComponents: [FuenteReferenciaXItemDeleteDialogComponent],
})
export class SusaludFuenteReferenciaXItemModule {}
