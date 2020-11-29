import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISubProceso } from 'app/shared/model/sub-proceso.model';
import { SubProcesoService } from './sub-proceso.service';
import { SubProcesoDeleteDialogComponent } from './sub-proceso-delete-dialog.component';

@Component({
  selector: 'jhi-sub-proceso',
  templateUrl: './sub-proceso.component.html',
})
export class SubProcesoComponent implements OnInit, OnDestroy {
  subProcesos?: ISubProceso[];
  eventSubscriber?: Subscription;

  constructor(protected subProcesoService: SubProcesoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.subProcesoService.query().subscribe((res: HttpResponse<ISubProceso[]>) => (this.subProcesos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSubProcesos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISubProceso): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSubProcesos(): void {
    this.eventSubscriber = this.eventManager.subscribe('subProcesoListModification', () => this.loadAll());
  }

  delete(subProceso: ISubProceso): void {
    const modalRef = this.modalService.open(SubProcesoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.subProceso = subProceso;
  }
}
