import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISubproceso } from 'app/shared/model/subproceso.model';
import { SubprocesoService } from './subproceso.service';
import { SubprocesoDeleteDialogComponent } from './subproceso-delete-dialog.component';

@Component({
  selector: 'jhi-subproceso',
  templateUrl: './subproceso.component.html',
})
export class SubprocesoComponent implements OnInit, OnDestroy {
  subprocesos?: ISubproceso[];
  eventSubscriber?: Subscription;

  constructor(protected subprocesoService: SubprocesoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.subprocesoService.query().subscribe((res: HttpResponse<ISubproceso[]>) => (this.subprocesos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSubprocesos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISubproceso): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSubprocesos(): void {
    this.eventSubscriber = this.eventManager.subscribe('subprocesoListModification', () => this.loadAll());
  }

  delete(subproceso: ISubproceso): void {
    const modalRef = this.modalService.open(SubprocesoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.subproceso = subproceso;
  }
}
