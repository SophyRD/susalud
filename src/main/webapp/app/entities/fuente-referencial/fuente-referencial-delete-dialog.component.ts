import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFuenteReferencial } from 'app/shared/model/fuente-referencial.model';
import { FuenteReferencialService } from './fuente-referencial.service';

@Component({
  templateUrl: './fuente-referencial-delete-dialog.component.html',
})
export class FuenteReferencialDeleteDialogComponent {
  fuenteReferencial?: IFuenteReferencial;

  constructor(
    protected fuenteReferencialService: FuenteReferencialService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fuenteReferencialService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fuenteReferencialListModification');
      this.activeModal.close();
    });
  }
}
