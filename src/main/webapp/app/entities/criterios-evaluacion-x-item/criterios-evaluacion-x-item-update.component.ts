import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICriteriosEvaluacionXItem, CriteriosEvaluacionXItem } from 'app/shared/model/criterios-evaluacion-x-item.model';
import { CriteriosEvaluacionXItemService } from './criterios-evaluacion-x-item.service';

@Component({
  selector: 'jhi-criterios-evaluacion-x-item-update',
  templateUrl: './criterios-evaluacion-x-item-update.component.html',
})
export class CriteriosEvaluacionXItemUpdateComponent implements OnInit {
  isSaving = false;
  ceiFechaCreacionDp: any;
  ceiFechaModificacionDp: any;

  editForm = this.fb.group({
    id: [],
    idCriteriosEvaluacionXItem: [null, [Validators.required]],
    ceiFechaCreacion: [null, [Validators.required]],
    ceiFechaModificacion: [null, [Validators.required]],
    ceiNombre: [null, [Validators.required]],
  });

  constructor(
    protected criteriosEvaluacionXItemService: CriteriosEvaluacionXItemService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ criteriosEvaluacionXItem }) => {
      this.updateForm(criteriosEvaluacionXItem);
    });
  }

  updateForm(criteriosEvaluacionXItem: ICriteriosEvaluacionXItem): void {
    this.editForm.patchValue({
      id: criteriosEvaluacionXItem.id,
      idCriteriosEvaluacionXItem: criteriosEvaluacionXItem.idCriteriosEvaluacionXItem,
      ceiFechaCreacion: criteriosEvaluacionXItem.ceiFechaCreacion,
      ceiFechaModificacion: criteriosEvaluacionXItem.ceiFechaModificacion,
      ceiNombre: criteriosEvaluacionXItem.ceiNombre,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const criteriosEvaluacionXItem = this.createFromForm();
    if (criteriosEvaluacionXItem.id !== undefined) {
      this.subscribeToSaveResponse(this.criteriosEvaluacionXItemService.update(criteriosEvaluacionXItem));
    } else {
      this.subscribeToSaveResponse(this.criteriosEvaluacionXItemService.create(criteriosEvaluacionXItem));
    }
  }

  private createFromForm(): ICriteriosEvaluacionXItem {
    return {
      ...new CriteriosEvaluacionXItem(),
      id: this.editForm.get(['id'])!.value,
      idCriteriosEvaluacionXItem: this.editForm.get(['idCriteriosEvaluacionXItem'])!.value,
      ceiFechaCreacion: this.editForm.get(['ceiFechaCreacion'])!.value,
      ceiFechaModificacion: this.editForm.get(['ceiFechaModificacion'])!.value,
      ceiNombre: this.editForm.get(['ceiNombre'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICriteriosEvaluacionXItem>>): void {
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
