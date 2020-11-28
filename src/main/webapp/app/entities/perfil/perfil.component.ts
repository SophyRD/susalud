import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IPerfil } from 'app/shared/model/perfil.model';
import { PerfilService } from './perfil.service';
import { PerfilDeleteDialogComponent } from './perfil-delete-dialog.component';

@Component({
  selector: 'jhi-perfil',
  templateUrl: './perfil.component.html',
})
export class PerfilComponent implements OnInit, OnDestroy {
  perfils?: IPerfil[];
  eventSubscriber?: Subscription;

  constructor(protected perfilService: PerfilService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.perfilService.query().subscribe((res: HttpResponse<IPerfil[]>) => (this.perfils = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInPerfils();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IPerfil): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInPerfils(): void {
    this.eventSubscriber = this.eventManager.subscribe('perfilListModification', () => this.loadAll());
  }

  delete(perfil: IPerfil): void {
    const modalRef = this.modalService.open(PerfilDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.perfil = perfil;
  }
}
