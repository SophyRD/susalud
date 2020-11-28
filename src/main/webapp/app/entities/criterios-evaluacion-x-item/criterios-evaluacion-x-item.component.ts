import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICriteriosEvaluacionXItem } from 'app/shared/model/criterios-evaluacion-x-item.model';
import { CriteriosEvaluacionXItemService } from './criterios-evaluacion-x-item.service';
import { CriteriosEvaluacionXItemDeleteDialogComponent } from './criterios-evaluacion-x-item-delete-dialog.component';

@Component({
  selector: 'jhi-criterios-evaluacion-x-item',
  templateUrl: './criterios-evaluacion-x-item.component.html',
})
export class CriteriosEvaluacionXItemComponent implements OnInit, OnDestroy {
  criteriosEvaluacionXItems?: ICriteriosEvaluacionXItem[];
  eventSubscriber?: Subscription;

  constructor(
    protected criteriosEvaluacionXItemService: CriteriosEvaluacionXItemService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.criteriosEvaluacionXItemService
      .query()
      .subscribe((res: HttpResponse<ICriteriosEvaluacionXItem[]>) => (this.criteriosEvaluacionXItems = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCriteriosEvaluacionXItems();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICriteriosEvaluacionXItem): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCriteriosEvaluacionXItems(): void {
    this.eventSubscriber = this.eventManager.subscribe('criteriosEvaluacionXItemListModification', () => this.loadAll());
  }

  delete(criteriosEvaluacionXItem: ICriteriosEvaluacionXItem): void {
    const modalRef = this.modalService.open(CriteriosEvaluacionXItemDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.criteriosEvaluacionXItem = criteriosEvaluacionXItem;
  }
}
