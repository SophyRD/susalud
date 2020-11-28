import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { CriteriosEvaluacionXItemComponent } from './criterios-evaluacion-x-item.component';
import { CriteriosEvaluacionXItemDetailComponent } from './criterios-evaluacion-x-item-detail.component';
import { CriteriosEvaluacionXItemUpdateComponent } from './criterios-evaluacion-x-item-update.component';
import { CriteriosEvaluacionXItemDeleteDialogComponent } from './criterios-evaluacion-x-item-delete-dialog.component';
import { criteriosEvaluacionXItemRoute } from './criterios-evaluacion-x-item.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(criteriosEvaluacionXItemRoute)],
  declarations: [
    CriteriosEvaluacionXItemComponent,
    CriteriosEvaluacionXItemDetailComponent,
    CriteriosEvaluacionXItemUpdateComponent,
    CriteriosEvaluacionXItemDeleteDialogComponent,
  ],
  entryComponents: [CriteriosEvaluacionXItemDeleteDialogComponent],
})
export class SusaludCriteriosEvaluacionXItemModule {}
