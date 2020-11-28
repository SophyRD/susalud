import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { CriteriosEvaluacionComponent } from './criterios-evaluacion.component';
import { CriteriosEvaluacionDetailComponent } from './criterios-evaluacion-detail.component';
import { CriteriosEvaluacionUpdateComponent } from './criterios-evaluacion-update.component';
import { CriteriosEvaluacionDeleteDialogComponent } from './criterios-evaluacion-delete-dialog.component';
import { criteriosEvaluacionRoute } from './criterios-evaluacion.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(criteriosEvaluacionRoute)],
  declarations: [
    CriteriosEvaluacionComponent,
    CriteriosEvaluacionDetailComponent,
    CriteriosEvaluacionUpdateComponent,
    CriteriosEvaluacionDeleteDialogComponent,
  ],
  entryComponents: [CriteriosEvaluacionDeleteDialogComponent],
})
export class SusaludCriteriosEvaluacionModule {}
