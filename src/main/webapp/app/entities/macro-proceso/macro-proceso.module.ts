import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { MacroProcesoComponent } from './macro-proceso.component';
import { MacroProcesoDetailComponent } from './macro-proceso-detail.component';
import { MacroProcesoUpdateComponent } from './macro-proceso-update.component';
import { MacroProcesoDeleteDialogComponent } from './macro-proceso-delete-dialog.component';
import { macroProcesoRoute } from './macro-proceso.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(macroProcesoRoute)],
  declarations: [MacroProcesoComponent, MacroProcesoDetailComponent, MacroProcesoUpdateComponent, MacroProcesoDeleteDialogComponent],
  entryComponents: [MacroProcesoDeleteDialogComponent],
})
export class SusaludMacroProcesoModule {}
