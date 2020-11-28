import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITecnicasEvaluativas } from 'app/shared/model/tecnicas-evaluativas.model';
import { TecnicasEvaluativasService } from './tecnicas-evaluativas.service';

@Component({
  templateUrl: './tecnicas-evaluativas-delete-dialog.component.html',
})
export class TecnicasEvaluativasDeleteDialogComponent {
  tecnicasEvaluativas?: ITecnicasEvaluativas;

  constructor(
    protected tecnicasEvaluativasService: TecnicasEvaluativasService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tecnicasEvaluativasService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tecnicasEvaluativasListModification');
      this.activeModal.close();
    });
  }
}
