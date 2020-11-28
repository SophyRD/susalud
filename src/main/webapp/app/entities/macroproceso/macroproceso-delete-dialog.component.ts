import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMacroproceso } from 'app/shared/model/macroproceso.model';
import { MacroprocesoService } from './macroproceso.service';

@Component({
  templateUrl: './macroproceso-delete-dialog.component.html',
})
export class MacroprocesoDeleteDialogComponent {
  macroproceso?: IMacroproceso;

  constructor(
    protected macroprocesoService: MacroprocesoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.macroprocesoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('macroprocesoListModification');
      this.activeModal.close();
    });
  }
}
