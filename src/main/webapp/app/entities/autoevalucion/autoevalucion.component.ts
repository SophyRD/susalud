import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IAutoevalucion } from 'app/shared/model/autoevalucion.model';
import { AutoevalucionService } from './autoevalucion.service';
import { AutoevalucionDeleteDialogComponent } from './autoevalucion-delete-dialog.component';

@Component({
  selector: 'jhi-autoevalucion',
  templateUrl: './autoevalucion.component.html',
})
export class AutoevalucionComponent implements OnInit, OnDestroy {
  autoevalucions?: IAutoevalucion[];
  eventSubscriber?: Subscription;

  constructor(
    protected autoevalucionService: AutoevalucionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.autoevalucionService.query().subscribe((res: HttpResponse<IAutoevalucion[]>) => (this.autoevalucions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInAutoevalucions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IAutoevalucion): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAutoevalucions(): void {
    this.eventSubscriber = this.eventManager.subscribe('autoevalucionListModification', () => this.loadAll());
  }

  delete(autoevalucion: IAutoevalucion): void {
    const modalRef = this.modalService.open(AutoevalucionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.autoevalucion = autoevalucion;
  }
}
