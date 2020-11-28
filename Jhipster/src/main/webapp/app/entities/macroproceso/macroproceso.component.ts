import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMacroproceso } from 'app/shared/model/macroproceso.model';
import { MacroprocesoService } from './macroproceso.service';
import { MacroprocesoDeleteDialogComponent } from './macroproceso-delete-dialog.component';

@Component({
  selector: 'jhi-macroproceso',
  templateUrl: './macroproceso.component.html',
})
export class MacroprocesoComponent implements OnInit, OnDestroy {
  macroprocesos?: IMacroproceso[];
  eventSubscriber?: Subscription;

  constructor(
    protected macroprocesoService: MacroprocesoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.macroprocesoService.query().subscribe((res: HttpResponse<IMacroproceso[]>) => (this.macroprocesos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMacroprocesos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMacroproceso): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMacroprocesos(): void {
    this.eventSubscriber = this.eventManager.subscribe('macroprocesoListModification', () => this.loadAll());
  }

  delete(macroproceso: IMacroproceso): void {
    const modalRef = this.modalService.open(MacroprocesoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.macroproceso = macroproceso;
  }
}
