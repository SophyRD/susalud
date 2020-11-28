import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICriteriosEvaluacion } from 'app/shared/model/criterios-evaluacion.model';
import { CriteriosEvaluacionService } from './criterios-evaluacion.service';

@Component({
  templateUrl: './criterios-evaluacion-delete-dialog.component.html',
})
export class CriteriosEvaluacionDeleteDialogComponent {
  criteriosEvaluacion?: ICriteriosEvaluacion;

  constructor(
    protected criteriosEvaluacionService: CriteriosEvaluacionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.criteriosEvaluacionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('criteriosEvaluacionListModification');
      this.activeModal.close();
    });
  }
}
