import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAutoevalucionXproceso } from 'app/shared/model/autoevalucion-xproceso.model';
import { AutoevalucionXprocesoService } from './autoevalucion-xproceso.service';
import { AutoevalucionXprocesoDeleteDialogComponent } from './autoevalucion-xproceso-delete-dialog.component';

@Component({
  selector: 'jhi-autoevalucion-xproceso',
  templateUrl: './autoevalucion-xproceso.component.html',
})
export class AutoevalucionXprocesoComponent implements OnInit, OnDestroy {
  autoevalucionXprocesos?: IAutoevalucionXproceso[];
  eventSubscriber?: Subscription;

  constructor(
    protected autoevalucionXprocesoService: AutoevalucionXprocesoService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.autoevalucionXprocesoService
      .query()
      .subscribe((res: HttpResponse<IAutoevalucionXproceso[]>) => (this.autoevalucionXprocesos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAutoevalucionXprocesos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAutoevalucionXproceso): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAutoevalucionXprocesos(): void {
    this.eventSubscriber = this.eventManager.subscribe('autoevalucionXprocesoListModification', () => this.loadAll());
  }

  delete(autoevalucionXproceso: IAutoevalucionXproceso): void {
    const modalRef = this.modalService.open(AutoevalucionXprocesoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.autoevalucionXproceso = autoevalucionXproceso;
  }
}
