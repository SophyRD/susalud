import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPeriodo } from 'app/shared/model/periodo.model';
import { PeriodoService } from './periodo.service';

@Component({
  templateUrl: './periodo-delete-dialog.component.html',
})
export class PeriodoDeleteDialogComponent {
  periodo?: IPeriodo;

  constructor(protected periodoService: PeriodoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.periodoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('periodoListModification');
      this.activeModal.close();
    });
  }
}
