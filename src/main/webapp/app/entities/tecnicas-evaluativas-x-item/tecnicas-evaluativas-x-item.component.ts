import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITecnicasEvaluativasXItem } from 'app/shared/model/tecnicas-evaluativas-x-item.model';
import { TecnicasEvaluativasXItemService } from './tecnicas-evaluativas-x-item.service';
import { TecnicasEvaluativasXItemDeleteDialogComponent } from './tecnicas-evaluativas-x-item-delete-dialog.component';

@Component({
  selector: 'jhi-tecnicas-evaluativas-x-item',
  templateUrl: './tecnicas-evaluativas-x-item.component.html',
})
export class TecnicasEvaluativasXItemComponent implements OnInit, OnDestroy {
  tecnicasEvaluativasXItems?: ITecnicasEvaluativasXItem[];
  eventSubscriber?: Subscription;

  constructor(
    protected tecnicasEvaluativasXItemService: TecnicasEvaluativasXItemService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tecnicasEvaluativasXItemService
      .query()
      .subscribe((res: HttpResponse<ITecnicasEvaluativasXItem[]>) => (this.tecnicasEvaluativasXItems = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTecnicasEvaluativasXItems();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITecnicasEvaluativasXItem): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTecnicasEvaluativasXItems(): void {
    this.eventSubscriber = this.eventManager.subscribe('tecnicasEvaluativasXItemListModification', () => this.loadAll());
  }

  delete(tecnicasEvaluativasXItem: ITecnicasEvaluativasXItem): void {
    const modalRef = this.modalService.open(TecnicasEvaluativasXItemDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tecnicasEvaluativasXItem = tecnicasEvaluativasXItem;
  }
}
