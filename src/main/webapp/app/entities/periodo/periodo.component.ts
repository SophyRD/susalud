import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPeriodo } from 'app/shared/model/periodo.model';
import { PeriodoService } from './periodo.service';
import { PeriodoDeleteDialogComponent } from './periodo-delete-dialog.component';

@Component({
  selector: 'jhi-periodo',
  templateUrl: './periodo.component.html',
})
export class PeriodoComponent implements OnInit, OnDestroy {
  periodos?: IPeriodo[];
  eventSubscriber?: Subscription;

  constructor(protected periodoService: PeriodoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.periodoService.query().subscribe((res: HttpResponse<IPeriodo[]>) => (this.periodos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPeriodos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPeriodo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPeriodos(): void {
    this.eventSubscriber = this.eventManager.subscribe('periodoListModification', () => this.loadAll());
  }

  delete(periodo: IPeriodo): void {
    const modalRef = this.modalService.open(PeriodoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.periodo = periodo;
  }
}
