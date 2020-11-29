import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMacroProceso } from 'app/shared/model/macro-proceso.model';
import { MacroProcesoService } from './macro-proceso.service';
import { MacroProcesoDeleteDialogComponent } from './macro-proceso-delete-dialog.component';

@Component({
  selector: 'jhi-macro-proceso',
  templateUrl: './macro-proceso.component.html',
})
export class MacroProcesoComponent implements OnInit, OnDestroy {
  macroProcesos?: IMacroProceso[];
  eventSubscriber?: Subscription;

  constructor(
    protected macroProcesoService: MacroProcesoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.macroProcesoService.query().subscribe((res: HttpResponse<IMacroProceso[]>) => (this.macroProcesos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMacroProcesos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMacroProceso): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMacroProcesos(): void {
    this.eventSubscriber = this.eventManager.subscribe('macroProcesoListModification', () => this.loadAll());
  }

  delete(macroProceso: IMacroProceso): void {
    const modalRef = this.modalService.open(MacroProcesoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.macroProceso = macroProceso;
  }
}
