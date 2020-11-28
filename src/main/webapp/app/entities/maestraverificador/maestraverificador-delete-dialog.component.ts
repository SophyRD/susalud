import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMaestraverificador } from 'app/shared/model/maestraverificador.model';
import { MaestraverificadorService } from './maestraverificador.service';

@Component({
  templateUrl: './maestraverificador-delete-dialog.component.html',
})
export class MaestraverificadorDeleteDialogComponent {
  maestraverificador?: IMaestraverificador;

  constructor(
    protected maestraverificadorService: MaestraverificadorService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.maestraverificadorService.delete(id).subscribe(() => {
      this.eventManager.broadcast('maestraverificadorListModification');
      this.activeModal.close();
    });
  }
}
