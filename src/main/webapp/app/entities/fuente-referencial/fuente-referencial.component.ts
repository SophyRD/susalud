import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFuenteReferencial } from 'app/shared/model/fuente-referencial.model';
import { FuenteReferencialService } from './fuente-referencial.service';
import { FuenteReferencialDeleteDialogComponent } from './fuente-referencial-delete-dialog.component';

@Component({
  selector: 'jhi-fuente-referencial',
  templateUrl: './fuente-referencial.component.html',
})
export class FuenteReferencialComponent implements OnInit, OnDestroy {
  fuenteReferencials?: IFuenteReferencial[];
  eventSubscriber?: Subscription;

  constructor(
    protected fuenteReferencialService: FuenteReferencialService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.fuenteReferencialService
      .query()
      .subscribe((res: HttpResponse<IFuenteReferencial[]>) => (this.fuenteReferencials = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFuenteReferencials();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFuenteReferencial): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFuenteReferencials(): void {
    this.eventSubscriber = this.eventManager.subscribe('fuenteReferencialListModification', () => this.loadAll());
  }

  delete(fuenteReferencial: IFuenteReferencial): void {
    const modalRef = this.modalService.open(FuenteReferencialDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fuenteReferencial = fuenteReferencial;
  }
}
