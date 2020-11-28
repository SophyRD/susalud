import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUsuarioXEvaluacioXEntidad } from 'app/shared/model/usuario-x-evaluacio-x-entidad.model';
import { UsuarioXEvaluacioXEntidadService } from './usuario-x-evaluacio-x-entidad.service';

@Component({
  templateUrl: './usuario-x-evaluacio-x-entidad-delete-dialog.component.html',
})
export class UsuarioXEvaluacioXEntidadDeleteDialogComponent {
  usuarioXEvaluacioXEntidad?: IUsuarioXEvaluacioXEntidad;

  constructor(
    protected usuarioXEvaluacioXEntidadService: UsuarioXEvaluacioXEntidadService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.usuarioXEvaluacioXEntidadService.delete(id).subscribe(() => {
      this.eventManager.broadcast('usuarioXEvaluacioXEntidadListModification');
      this.activeModal.close();
    });
  }
}
