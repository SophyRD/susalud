import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFuenteReferenciaXItem, FuenteReferenciaXItem } from 'app/shared/model/fuente-referencia-x-item.model';
import { FuenteReferenciaXItemService } from './fuente-referencia-x-item.service';

@Component({
  selector: 'jhi-fuente-referencia-x-item-update',
  templateUrl: './fuente-referencia-x-item-update.component.html',
})
export class FuenteReferenciaXItemUpdateComponent implements OnInit {
  isSaving = false;
  friFechaCreacionDp: any;
  friFechaModificacionDp: any;

  editForm = this.fb.group({
    id: [],
    idFuenteReferenciaXItem: [null, [Validators.required]],
    friFechaCreacion: [null, [Validators.required]],
    friFechaModificacion: [null, [Validators.required]],
    friNombre: [null, [Validators.required]],
  });

  constructor(
    protected fuenteReferenciaXItemService: FuenteReferenciaXItemService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fuenteReferenciaXItem }) => {
      this.updateForm(fuenteReferenciaXItem);
    });
  }

  updateForm(fuenteReferenciaXItem: IFuenteReferenciaXItem): void {
    this.editForm.patchValue({
      id: fuenteReferenciaXItem.id,
      idFuenteReferenciaXItem: fuenteReferenciaXItem.idFuenteReferenciaXItem,
      friFechaCreacion: fuenteReferenciaXItem.friFechaCreacion,
      friFechaModificacion: fuenteReferenciaXItem.friFechaModificacion,
      friNombre: fuenteReferenciaXItem.friNombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fuenteReferenciaXItem = this.createFromForm();
    if (fuenteReferenciaXItem.id !== undefined) {
      this.subscribeToSaveResponse(this.fuenteReferenciaXItemService.update(fuenteReferenciaXItem));
    } else {
      this.subscribeToSaveResponse(this.fuenteReferenciaXItemService.create(fuenteReferenciaXItem));
    }
  }

  private createFromForm(): IFuenteReferenciaXItem {
    return {
      ...new FuenteReferenciaXItem(),
      id: this.editForm.get(['id'])!.value,
      idFuenteReferenciaXItem: this.editForm.get(['idFuenteReferenciaXItem'])!.value,
      friFechaCreacion: this.editForm.get(['friFechaCreacion'])!.value,
      friFechaModificacion: this.editForm.get(['friFechaModificacion'])!.value,
      friNombre: this.editForm.get(['friNombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFuenteReferenciaXItem>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
