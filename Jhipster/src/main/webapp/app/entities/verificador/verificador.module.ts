import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProyectoSharedModule } from 'app/shared/shared.module';
import { VerificadorComponent } from './verificador.component';
import { VerificadorDetailComponent } from './verificador-detail.component';
import { VerificadorUpdateComponent } from './verificador-update.component';
import { VerificadorDeleteDialogComponent } from './verificador-delete-dialog.component';
import { verificadorRoute } from './verificador.route';

@NgModule({
  imports: [ProyectoSharedModule, RouterModule.forChild(verificadorRoute)],
  declarations: [VerificadorComponent, VerificadorDetailComponent, VerificadorUpdateComponent, VerificadorDeleteDialogComponent],
  entryComponents: [VerificadorDeleteDialogComponent],
})
export class ProyectoVerificadorModule {}
