import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { AutoevalucionXprocesoComponent } from './autoevalucion-xproceso.component';
import { AutoevalucionXprocesoDetailComponent } from './autoevalucion-xproceso-detail.component';
import { AutoevalucionXprocesoUpdateComponent } from './autoevalucion-xproceso-update.component';
import { AutoevalucionXprocesoDeleteDialogComponent } from './autoevalucion-xproceso-delete-dialog.component';
import { autoevalucionXprocesoRoute } from './autoevalucion-xproceso.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(autoevalucionXprocesoRoute)],
  declarations: [
    AutoevalucionXprocesoComponent,
    AutoevalucionXprocesoDetailComponent,
    AutoevalucionXprocesoUpdateComponent,
    AutoevalucionXprocesoDeleteDialogComponent,
  ],
  entryComponents: [AutoevalucionXprocesoDeleteDialogComponent],
})
export class SusaludAutoevalucionXprocesoModule {}
