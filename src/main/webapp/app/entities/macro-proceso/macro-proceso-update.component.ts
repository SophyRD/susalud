import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMacroProceso, MacroProceso } from 'app/shared/model/macro-proceso.model';
import { MacroProcesoService } from './macro-proceso.service';

@Component({
  selector: 'jhi-macro-proceso-update',
  templateUrl: './macro-proceso-update.component.html',
})
export class MacroProcesoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idMacroproceso: [],
    maFechaCreacion: [],
    verificadorIdFerificador: [],
  });

  constructor(protected macroProcesoService: MacroProcesoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ macroProceso }) => {
      this.updateForm(macroProceso);
    });
  }

  updateForm(macroProceso: IMacroProceso): void {
    this.editForm.patchValue({
      id: macroProceso.id,
      idMacroproceso: macroProceso.idMacroproceso,
      maFechaCreacion: macroProceso.maFechaCreacion,
      verificadorIdFerificador: macroProceso.verificadorIdFerificador,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const macroProceso = this.createFromForm();
    if (macroProceso.id !== undefined) {
      this.subscribeToSaveResponse(this.macroProcesoService.update(macroProceso));
    } else {
      this.subscribeToSaveResponse(this.macroProcesoService.create(macroProceso));
    }
  }

  private createFromForm(): IMacroProceso {
    return {
      ...new MacroProceso(),
      id: this.editForm.get(['id'])!.value,
      idMacroproceso: this.editForm.get(['idMacroproceso'])!.value,
      maFechaCreacion: this.editForm.get(['maFechaCreacion'])!.value,
      verificadorIdFerificador: this.editForm.get(['verificadorIdFerificador'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMacroProceso>>): void {
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
