import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUsuarioXEvaluacioXEntidad } from 'app/shared/model/usuario-x-evaluacio-x-entidad.model';
import { UsuarioXEvaluacioXEntidadService } from './usuario-x-evaluacio-x-entidad.service';
import { UsuarioXEvaluacioXEntidadDeleteDialogComponent } from './usuario-x-evaluacio-x-entidad-delete-dialog.component';

@Component({
  selector: 'jhi-usuario-x-evaluacio-x-entidad',
  templateUrl: './usuario-x-evaluacio-x-entidad.component.html',
})
export class UsuarioXEvaluacioXEntidadComponent implements OnInit, OnDestroy {
  usuarioXEvaluacioXEntidads?: IUsuarioXEvaluacioXEntidad[];
  eventSubscriber?: Subscription;

  constructor(
    protected usuarioXEvaluacioXEntidadService: UsuarioXEvaluacioXEntidadService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.usuarioXEvaluacioXEntidadService
      .query()
      .subscribe((res: HttpResponse<IUsuarioXEvaluacioXEntidad[]>) => (this.usuarioXEvaluacioXEntidads = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUsuarioXEvaluacioXEntidads();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUsuarioXEvaluacioXEntidad): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUsuarioXEvaluacioXEntidads(): void {
    this.eventSubscriber = this.eventManager.subscribe('usuarioXEvaluacioXEntidadListModification', () => this.loadAll());
  }

  delete(usuarioXEvaluacioXEntidad: IUsuarioXEvaluacioXEntidad): void {
    const modalRef = this.modalService.open(UsuarioXEvaluacioXEntidadDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.usuarioXEvaluacioXEntidad = usuarioXEvaluacioXEntidad;
  }
}
