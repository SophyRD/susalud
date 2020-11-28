import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITecnicasEvaluativasXItem } from 'app/shared/model/tecnicas-evaluativas-x-item.model';
import { TecnicasEvaluativasXItemService } from './tecnicas-evaluativas-x-item.service';

@Component({
  templateUrl: './tecnicas-evaluativas-x-item-delete-dialog.component.html',
})
export class TecnicasEvaluativasXItemDeleteDialogComponent {
  tecnicasEvaluativasXItem?: ITecnicasEvaluativasXItem;

  constructor(
    protected tecnicasEvaluativasXItemService: TecnicasEvaluativasXItemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tecnicasEvaluativasXItemService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tecnicasEvaluativasXItemListModification');
      this.activeModal.close();
    });
  }
}
