import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { MaestraverificadorComponent } from './maestraverificador.component';
import { MaestraverificadorDetailComponent } from './maestraverificador-detail.component';
import { MaestraverificadorUpdateComponent } from './maestraverificador-update.component';
import { MaestraverificadorDeleteDialogComponent } from './maestraverificador-delete-dialog.component';
import { maestraverificadorRoute } from './maestraverificador.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(maestraverificadorRoute)],
  declarations: [
    MaestraverificadorComponent,
    MaestraverificadorDetailComponent,
    MaestraverificadorUpdateComponent,
    MaestraverificadorDeleteDialogComponent,
  ],
  entryComponents: [MaestraverificadorDeleteDialogComponent],
})
export class SusaludMaestraverificadorModule {}
