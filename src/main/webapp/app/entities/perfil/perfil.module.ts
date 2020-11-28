import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SusaludSharedModule } from 'app/shared/shared.module';
import { PerfilComponent } from './perfil.component';
import { PerfilDetailComponent } from './perfil-detail.component';
import { PerfilUpdateComponent } from './perfil-update.component';
import { PerfilDeleteDialogComponent } from './perfil-delete-dialog.component';
import { perfilRoute } from './perfil.route';

@NgModule({
  imports: [SusaludSharedModule, RouterModule.forChild(perfilRoute)],
  declarations: [PerfilComponent, PerfilDetailComponent, PerfilUpdateComponent, PerfilDeleteDialogComponent],
  entryComponents: [PerfilDeleteDialogComponent],
})
export class SusaludPerfilModule {}
