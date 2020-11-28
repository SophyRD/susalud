import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { TecnicasEvaluativasXItemComponent } from './tecnicas-evaluativas-x-item.component';
import { TecnicasEvaluativasXItemDetailComponent } from './tecnicas-evaluativas-x-item-detail.component';
import { TecnicasEvaluativasXItemUpdateComponent } from './tecnicas-evaluativas-x-item-update.component';
import { TecnicasEvaluativasXItemDeleteDialogComponent } from './tecnicas-evaluativas-x-item-delete-dialog.component';
import { tecnicasEvaluativasXItemRoute } from './tecnicas-evaluativas-x-item.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(tecnicasEvaluativasXItemRoute)],
  declarations: [
    TecnicasEvaluativasXItemComponent,
    TecnicasEvaluativasXItemDetailComponent,
    TecnicasEvaluativasXItemUpdateComponent,
    TecnicasEvaluativasXItemDeleteDialogComponent,
  ],
  entryComponents: [TecnicasEvaluativasXItemDeleteDialogComponent],
})
export class SusaludTecnicasEvaluativasXItemModule {}
