import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IProceso } from 'app/shared/model/proceso.model';
import { ProcesoService } from './proceso.service';
import { ProcesoDeleteDialogComponent } from './proceso-delete-dialog.component';

@Component({
  selector: 'jhi-proceso',
  templateUrl: './proceso.component.html',
})
export class ProcesoComponent implements OnInit, OnDestroy {
  procesos?: IProceso[];
  eventSubscriber?: Subscription;

  constructor(protected procesoService: ProcesoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.procesoService.query().subscribe((res: HttpResponse<IProceso[]>) => (this.procesos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInProcesos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IProceso): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProcesos(): void {
    this.eventSubscriber = this.eventManager.subscribe('procesoListModification', () => this.loadAll());
  }

  delete(proceso: IProceso): void {
    const modalRef = this.modalService.open(ProcesoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.proceso = proceso;
  }
}
