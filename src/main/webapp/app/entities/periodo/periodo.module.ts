import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { PeriodoComponent } from './periodo.component';
import { PeriodoDetailComponent } from './periodo-detail.component';
import { PeriodoUpdateComponent } from './periodo-update.component';
import { PeriodoDeleteDialogComponent } from './periodo-delete-dialog.component';
import { periodoRoute } from './periodo.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(periodoRoute)],
  declarations: [PeriodoComponent, PeriodoDetailComponent, PeriodoUpdateComponent, PeriodoDeleteDialogComponent],
  entryComponents: [PeriodoDeleteDialogComponent],
})
export class SusaludPeriodoModule {}
