import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICriteriosEvaluacion } from 'app/shared/model/criterios-evaluacion.model';
import { CriteriosEvaluacionService } from './criterios-evaluacion.service';
import { CriteriosEvaluacionDeleteDialogComponent } from './criterios-evaluacion-delete-dialog.component';

@Component({
  selector: 'jhi-criterios-evaluacion',
  templateUrl: './criterios-evaluacion.component.html',
})
export class CriteriosEvaluacionComponent implements OnInit, OnDestroy {
  criteriosEvaluacions?: ICriteriosEvaluacion[];
  eventSubscriber?: Subscription;

  constructor(
    protected criteriosEvaluacionService: CriteriosEvaluacionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.criteriosEvaluacionService
      .query()
      .subscribe((res: HttpResponse<ICriteriosEvaluacion[]>) => (this.criteriosEvaluacions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCriteriosEvaluacions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICriteriosEvaluacion): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCriteriosEvaluacions(): void {
    this.eventSubscriber = this.eventManager.subscribe('criteriosEvaluacionListModification', () => this.loadAll());
  }

  delete(criteriosEvaluacion: ICriteriosEvaluacion): void {
    const modalRef = this.modalService.open(CriteriosEvaluacionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.criteriosEvaluacion = criteriosEvaluacion;
  }
}
