import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubProceso } from 'app/shared/model/sub-proceso.model';
import { SubProcesoService } from './sub-proceso.service';

@Component({
  templateUrl: './sub-proceso-delete-dialog.component.html',
})
export class SubProcesoDeleteDialogComponent {
  subProceso?: ISubProceso;

  constructor(
    protected subProcesoService: SubProcesoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.subProcesoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subProcesoListModification');
      this.activeModal.close();
    });
  }
}
