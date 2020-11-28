import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ITecnicasEvaluativas } from 'app/shared/model/tecnicas-evaluativas.model';
import { TecnicasEvaluativasService } from './tecnicas-evaluativas.service';
import { TecnicasEvaluativasDeleteDialogComponent } from './tecnicas-evaluativas-delete-dialog.component';

@Component({
  selector: 'jhi-tecnicas-evaluativas',
  templateUrl: './tecnicas-evaluativas.component.html',
})
export class TecnicasEvaluativasComponent implements OnInit, OnDestroy {
  tecnicasEvaluativas?: ITecnicasEvaluativas[];
  eventSubscriber?: Subscription;

  constructor(
    protected tecnicasEvaluativasService: TecnicasEvaluativasService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.tecnicasEvaluativasService
      .query()
      .subscribe((res: HttpResponse<ITecnicasEvaluativas[]>) => (this.tecnicasEvaluativas = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInTecnicasEvaluativas();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ITecnicasEvaluativas): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInTecnicasEvaluativas(): void {
    this.eventSubscriber = this.eventManager.subscribe('tecnicasEvaluativasListModification', () => this.loadAll());
  }

  delete(tecnicasEvaluativas: ITecnicasEvaluativas): void {
    const modalRef = this.modalService.open(TecnicasEvaluativasDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.tecnicasEvaluativas = tecnicasEvaluativas;
  }
}
