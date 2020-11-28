import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAutoevalucionXproceso } from 'app/shared/model/autoevalucion-xproceso.model';
import { AutoevalucionXprocesoService } from './autoevalucion-xproceso.service';

@Component({
  templateUrl: './autoevalucion-xproceso-delete-dialog.component.html',
})
export class AutoevalucionXprocesoDeleteDialogComponent {
  autoevalucionXproceso?: IAutoevalucionXproceso;

  constructor(
    protected autoevalucionXprocesoService: AutoevalucionXprocesoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.autoevalucionXprocesoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('autoevalucionXprocesoListModification');
      this.activeModal.close();
    });
  }
}
