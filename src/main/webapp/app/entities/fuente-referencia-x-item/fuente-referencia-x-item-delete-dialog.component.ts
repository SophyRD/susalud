import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFuenteReferenciaXItem } from 'app/shared/model/fuente-referencia-x-item.model';
import { FuenteReferenciaXItemService } from './fuente-referencia-x-item.service';

@Component({
  templateUrl: './fuente-referencia-x-item-delete-dialog.component.html',
})
export class FuenteReferenciaXItemDeleteDialogComponent {
  fuenteReferenciaXItem?: IFuenteReferenciaXItem;

  constructor(
    protected fuenteReferenciaXItemService: FuenteReferenciaXItemService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fuenteReferenciaXItemService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fuenteReferenciaXItemListModification');
      this.activeModal.close();
    });
  }
}
