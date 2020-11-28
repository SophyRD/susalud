import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUsuariosXevaluacion } from 'app/shared/model/usuarios-xevaluacion.model';
import { UsuariosXevaluacionService } from './usuarios-xevaluacion.service';

@Component({
  templateUrl: './usuarios-xevaluacion-delete-dialog.component.html',
})
export class UsuariosXevaluacionDeleteDialogComponent {
  usuariosXevaluacion?: IUsuariosXevaluacion;

  constructor(
    protected usuariosXevaluacionService: UsuariosXevaluacionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.usuariosXevaluacionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('usuariosXevaluacionListModification');
      this.activeModal.close();
    });
  }
}
