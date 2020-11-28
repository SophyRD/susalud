import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IUsuariosXevaluacion } from 'app/shared/model/usuarios-xevaluacion.model';
import { UsuariosXevaluacionService } from './usuarios-xevaluacion.service';
import { UsuariosXevaluacionDeleteDialogComponent } from './usuarios-xevaluacion-delete-dialog.component';

@Component({
  selector: 'jhi-usuarios-xevaluacion',
  templateUrl: './usuarios-xevaluacion.component.html',
})
export class UsuariosXevaluacionComponent implements OnInit, OnDestroy {
  usuariosXevaluacions?: IUsuariosXevaluacion[];
  eventSubscriber?: Subscription;

  constructor(
    protected usuariosXevaluacionService: UsuariosXevaluacionService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.usuariosXevaluacionService
      .query()
      .subscribe((res: HttpResponse<IUsuariosXevaluacion[]>) => (this.usuariosXevaluacions = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInUsuariosXevaluacions();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IUsuariosXevaluacion): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInUsuariosXevaluacions(): void {
    this.eventSubscriber = this.eventManager.subscribe('usuariosXevaluacionListModification', () => this.loadAll());
  }

  delete(usuariosXevaluacion: IUsuariosXevaluacion): void {
    const modalRef = this.modalService.open(UsuariosXevaluacionDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.usuariosXevaluacion = usuariosXevaluacion;
  }
}
