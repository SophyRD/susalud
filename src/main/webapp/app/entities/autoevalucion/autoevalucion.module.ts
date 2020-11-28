import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { AutoevalucionComponent } from './autoevalucion.component';
import { AutoevalucionDetailComponent } from './autoevalucion-detail.component';
import { AutoevalucionUpdateComponent } from './autoevalucion-update.component';
import { AutoevalucionDeleteDialogComponent } from './autoevalucion-delete-dialog.component';
import { autoevalucionRoute } from './autoevalucion.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(autoevalucionRoute)],
  declarations: [AutoevalucionComponent, AutoevalucionDetailComponent, AutoevalucionUpdateComponent, AutoevalucionDeleteDialogComponent],
  entryComponents: [AutoevalucionDeleteDialogComponent],
})
export class SusaludAutoevalucionModule {}
