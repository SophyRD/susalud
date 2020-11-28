import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAutoevalucion } from 'app/shared/model/autoevalucion.model';
import { AutoevalucionService } from './autoevalucion.service';

@Component({
  templateUrl: './autoevalucion-delete-dialog.component.html',
})
export class AutoevalucionDeleteDialogComponent {
  autoevalucion?: IAutoevalucion;

  constructor(
    protected autoevalucionService: AutoevalucionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autoevalucionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('autoevalucionListModification');
      this.activeModal.close();
    });
  }
}
