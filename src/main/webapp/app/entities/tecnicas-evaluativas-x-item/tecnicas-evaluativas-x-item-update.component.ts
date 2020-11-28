import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITecnicasEvaluativasXItem, TecnicasEvaluativasXItem } from 'app/shared/model/tecnicas-evaluativas-x-item.model';
import { TecnicasEvaluativasXItemService } from './tecnicas-evaluativas-x-item.service';

@Component({
  selector: 'jhi-tecnicas-evaluativas-x-item-update',
  templateUrl: './tecnicas-evaluativas-x-item-update.component.html',
})
export class TecnicasEvaluativasXItemUpdateComponent implements OnInit {
  isSaving = false;
  tiFechaCreacionDp: any;
  tiFechaModificacionDp: any;

  editForm = this.fb.group({
    id: [],
    idTecnicasEvaluativasXItem: [null, [Validators.required]],
    tiFechaCreacion: [null, [Validators.required]],
    tiFechaModificacion: [null, [Validators.required]],
    tiNombre: [null, [Validators.required]],
  });

  constructor(
    protected tecnicasEvaluativasXItemService: TecnicasEvaluativasXItemService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tecnicasEvaluativasXItem }) => {
      this.updateForm(tecnicasEvaluativasXItem);
    });
  }

  updateForm(tecnicasEvaluativasXItem: ITecnicasEvaluativasXItem): void {
    this.editForm.patchValue({
      id: tecnicasEvaluativasXItem.id,
      idTecnicasEvaluativasXItem: tecnicasEvaluativasXItem.idTecnicasEvaluativasXItem,
      tiFechaCreacion: tecnicasEvaluativasXItem.tiFechaCreacion,
      tiFechaModificacion: tecnicasEvaluativasXItem.tiFechaModificacion,
      tiNombre: tecnicasEvaluativasXItem.tiNombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tecnicasEvaluativasXItem = this.createFromForm();
    if (tecnicasEvaluativasXItem.id !== undefined) {
      this.subscribeToSaveResponse(this.tecnicasEvaluativasXItemService.update(tecnicasEvaluativasXItem));
    } else {
      this.subscribeToSaveResponse(this.tecnicasEvaluativasXItemService.create(tecnicasEvaluativasXItem));
    }
  }

  private createFromForm(): ITecnicasEvaluativasXItem {
    return {
      ...new TecnicasEvaluativasXItem(),
      id: this.editForm.get(['id'])!.value,
      idTecnicasEvaluativasXItem: this.editForm.get(['idTecnicasEvaluativasXItem'])!.value,
      tiFechaCreacion: this.editForm.get(['tiFechaCreacion'])!.value,
      tiFechaModificacion: this.editForm.get(['tiFechaModificacion'])!.value,
      tiNombre: this.editForm.get(['tiNombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITecnicasEvaluativasXItem>>): void {
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
