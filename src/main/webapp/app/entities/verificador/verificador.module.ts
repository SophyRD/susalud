import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { VerificadorComponent } from './verificador.component';
import { VerificadorDetailComponent } from './verificador-detail.component';
import { VerificadorUpdateComponent } from './verificador-update.component';
import { VerificadorDeleteDialogComponent } from './verificador-delete-dialog.component';
import { verificadorRoute } from './verificador.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(verificadorRoute)],
  declarations: [VerificadorComponent, VerificadorDetailComponent, VerificadorUpdateComponent, VerificadorDeleteDialogComponent],
  entryComponents: [VerificadorDeleteDialogComponent],
})
export class SusaludVerificadorModule {}
