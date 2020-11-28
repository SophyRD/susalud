import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IMaestraverificador } from 'app/shared/model/maestraverificador.model';
import { MaestraverificadorService } from './maestraverificador.service';
import { MaestraverificadorDeleteDialogComponent } from './maestraverificador-delete-dialog.component';

@Component({
  selector: 'jhi-maestraverificador',
  templateUrl: './maestraverificador.component.html',
})
export class MaestraverificadorComponent implements OnInit, OnDestroy {
  maestraverificadors?: IMaestraverificador[];
  eventSubscriber?: Subscription;

  constructor(
    protected maestraverificadorService: MaestraverificadorService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.maestraverificadorService
      .query()
      .subscribe((res: HttpResponse<IMaestraverificador[]>) => (this.maestraverificadors = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInMaestraverificadors();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IMaestraverificador): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInMaestraverificadors(): void {
    this.eventSubscriber = this.eventManager.subscribe('maestraverificadorListModification', () => this.loadAll());
  }

  delete(maestraverificador: IMaestraverificador): void {
    const modalRef = this.modalService.open(MaestraverificadorDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.maestraverificador = maestraverificador;
  }
}
