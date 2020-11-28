import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISubproceso } from 'app/shared/model/subproceso.model';
import { SubprocesoService } from './subproceso.service';

@Component({
  templateUrl: './subproceso-delete-dialog.component.html',
})
export class SubprocesoDeleteDialogComponent {
  subproceso?: ISubproceso;

  constructor(
    protected subprocesoService: SubprocesoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.subprocesoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('subprocesoListModification');
      this.activeModal.close();
    });
  }
}
