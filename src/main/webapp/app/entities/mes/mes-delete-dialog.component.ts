import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMes } from 'app/shared/model/mes.model';
import { MesService } from './mes.service';

@Component({
  templateUrl: './mes-delete-dialog.component.html',
})
export class MesDeleteDialogComponent {
  mes?: IMes;

  constructor(protected mesService: MesService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.mesService.delete(id).subscribe(() => {
      this.eventManager.broadcast('mesListModification');
      this.activeModal.close();
    });
  }
}
