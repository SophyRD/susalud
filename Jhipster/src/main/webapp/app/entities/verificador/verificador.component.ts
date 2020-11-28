import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVerificador } from 'app/shared/model/verificador.model';
import { VerificadorService } from './verificador.service';
import { VerificadorDeleteDialogComponent } from './verificador-delete-dialog.component';

@Component({
  selector: 'jhi-verificador',
  templateUrl: './verificador.component.html',
})
export class VerificadorComponent implements OnInit, OnDestroy {
  verificadors?: IVerificador[];
  eventSubscriber?: Subscription;

  constructor(
    protected verificadorService: VerificadorService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.verificadorService.query().subscribe((res: HttpResponse<IVerificador[]>) => (this.verificadors = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInVerificadors();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVerificador): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVerificadors(): void {
    this.eventSubscriber = this.eventManager.subscribe('verificadorListModification', () => this.loadAll());
  }

  delete(verificador: IVerificador): void {
    const modalRef = this.modalService.open(VerificadorDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.verificador = verificador;
  }
}
