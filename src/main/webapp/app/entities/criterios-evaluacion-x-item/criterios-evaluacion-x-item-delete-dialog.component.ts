import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICriteriosEvaluacionXItem } from 'app/shared/model/criterios-evaluacion-x-item.model';
import { CriteriosEvaluacionXItemService } from './criterios-evaluacion-x-item.service';

@Component({
  templateUrl: './criterios-evaluacion-x-item-delete-dialog.component.html',
})
export class CriteriosEvaluacionXItemDeleteDialogComponent {
  criteriosEvaluacionXItem?: ICriteriosEvaluacionXItem;

  constructor(
    protected criteriosEvaluacionXItemService: CriteriosEvaluacionXItemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.criteriosEvaluacionXItemService.delete(id).subscribe(() => {
      this.eventManager.broadcast('criteriosEvaluacionXItemListModification');
      this.activeModal.close();
    });
  }
}
