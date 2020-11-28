import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { MesComponent } from './mes.component';
import { MesDetailComponent } from './mes-detail.component';
import { MesUpdateComponent } from './mes-update.component';
import { MesDeleteDialogComponent } from './mes-delete-dialog.component';
import { mesRoute } from './mes.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(mesRoute)],
  declarations: [MesComponent, MesDetailComponent, MesUpdateComponent, MesDeleteDialogComponent],
  entryComponents: [MesDeleteDialogComponent],
})
export class SusaludMesModule {}
