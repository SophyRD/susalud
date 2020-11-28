import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFuenteReferenciaXItem } from 'app/shared/model/fuente-referencia-x-item.model';
import { FuenteReferenciaXItemService } from './fuente-referencia-x-item.service';
import { FuenteReferenciaXItemDeleteDialogComponent } from './fuente-referencia-x-item-delete-dialog.component';

@Component({
  selector: 'jhi-fuente-referencia-x-item',
  templateUrl: './fuente-referencia-x-item.component.html',
})
export class FuenteReferenciaXItemComponent implements OnInit, OnDestroy {
  fuenteReferenciaXItems?: IFuenteReferenciaXItem[];
  eventSubscriber?: Subscription;

  constructor(
    protected fuenteReferenciaXItemService: FuenteReferenciaXItemService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.fuenteReferenciaXItemService
      .query()
      .subscribe((res: HttpResponse<IFuenteReferenciaXItem[]>) => (this.fuenteReferenciaXItems = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFuenteReferenciaXItems();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFuenteReferenciaXItem): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFuenteReferenciaXItems(): void {
    this.eventSubscriber = this.eventManager.subscribe('fuenteReferenciaXItemListModification', () => this.loadAll());
  }

  delete(fuenteReferenciaXItem: IFuenteReferenciaXItem): void {
    const modalRef = this.modalService.open(FuenteReferenciaXItemDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fuenteReferenciaXItem = fuenteReferenciaXItem;
  }
}
