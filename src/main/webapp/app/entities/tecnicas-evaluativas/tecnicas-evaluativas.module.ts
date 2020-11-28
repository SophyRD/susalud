import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { TecnicasEvaluativasComponent } from './tecnicas-evaluativas.component';
import { TecnicasEvaluativasDetailComponent } from './tecnicas-evaluativas-detail.component';
import { TecnicasEvaluativasUpdateComponent } from './tecnicas-evaluativas-update.component';
import { TecnicasEvaluativasDeleteDialogComponent } from './tecnicas-evaluativas-delete-dialog.component';
import { tecnicasEvaluativasRoute } from './tecnicas-evaluativas.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(tecnicasEvaluativasRoute)],
  declarations: [
    TecnicasEvaluativasComponent,
    TecnicasEvaluativasDetailComponent,
    TecnicasEvaluativasUpdateComponent,
    TecnicasEvaluativasDeleteDialogComponent,
  ],
  entryComponents: [TecnicasEvaluativasDeleteDialogComponent],
})
export class SusaludTecnicasEvaluativasModule {}
