import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVerificador } from 'app/shared/model/verificador.model';
import { VerificadorService } from './verificador.service';

@Component({
  templateUrl: './verificador-delete-dialog.component.html',
})
export class VerificadorDeleteDialogComponent {
  verificador?: IVerificador;

  constructor(
    protected verificadorService: VerificadorService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.verificadorService.delete(id).subscribe(() => {
      this.eventManager.broadcast('verificadorListModification');
      this.activeModal.close();
    });
  }
}
