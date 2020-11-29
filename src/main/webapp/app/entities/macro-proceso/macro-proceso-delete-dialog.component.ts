import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMacroProceso } from 'app/shared/model/macro-proceso.model';
import { MacroProcesoService } from './macro-proceso.service';

@Component({
  templateUrl: './macro-proceso-delete-dialog.component.html',
})
export class MacroProcesoDeleteDialogComponent {
  macroProceso?: IMacroProceso;

  constructor(
    protected macroProcesoService: MacroProcesoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.macroProcesoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('macroProcesoListModification');
      this.activeModal.close();
    });
  }
}
