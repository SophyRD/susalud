import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMes } from 'app/shared/model/mes.model';
import { MesService } from './mes.service';
import { MesDeleteDialogComponent } from './mes-delete-dialog.component';

@Component({
  selector: 'jhi-mes',
  templateUrl: './mes.component.html',
})
export class MesComponent implements OnInit, OnDestroy {
  mes?: IMes[];
  eventSubscriber?: Subscription;

  constructor(protected mesService: MesService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.mesService.query().subscribe((res: HttpResponse<IMes[]>) => (this.mes = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMes): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMes(): void {
    this.eventSubscriber = this.eventManager.subscribe('mesListModification', () => this.loadAll());
  }

  delete(mes: IMes): void {
    const modalRef = this.modalService.open(MesDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.mes = mes;
  }
}
