import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICriteriosEvaluacion, CriteriosEvaluacion } from 'app/shared/model/criterios-evaluacion.model';
import { CriteriosEvaluacionService } from './criterios-evaluacion.service';

@Component({
  selector: 'jhi-criterios-evaluacion-update',
  templateUrl: './criterios-evaluacion-update.component.html',
})
export class CriteriosEvaluacionUpdateComponent implements OnInit {
  isSaving = false;
  ceFechaCreacionDp: any;
  ceFechaModificacionDp: any;

  editForm = this.fb.group({
    id: [],
    idCriteriosEvaluacion: [null, [Validators.required]],
    ceNombre: [null, [Validators.required]],
    ceFechaCreacion: [null, [Validators.required]],
    ceFechaModificacion: [null, [Validators.required]],
    idAutoevalucionXproceso: [null, [Validators.required]],
    idAutoevalucion: [null, [Validators.required]],
    idMes: [null, [Validators.required]],
    idUsuariosXevaluacion: [null, [Validators.required]],
    idEstado: [null, [Validators.required]],
    idVerificador: [null, [Validators.required]],
    idCriteriosEvaluacionXItem: [null, [Validators.required]],
  });

  constructor(
    protected criteriosEvaluacionService: CriteriosEvaluacionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ criteriosEvaluacion }) => {
      this.updateForm(criteriosEvaluacion);
    });
  }

  updateForm(criteriosEvaluacion: ICriteriosEvaluacion): void {
    this.editForm.patchValue({
      id: criteriosEvaluacion.id,
      idCriteriosEvaluacion: criteriosEvaluacion.idCriteriosEvaluacion,
      ceNombre: criteriosEvaluacion.ceNombre,
      ceFechaCreacion: criteriosEvaluacion.ceFechaCreacion,
      ceFechaModificacion: criteriosEvaluacion.ceFechaModificacion,
      idAutoevalucionXproceso: criteriosEvaluacion.idAutoevalucionXproceso,
      idAutoevalucion: criteriosEvaluacion.idAutoevalucion,
      idMes: criteriosEvaluacion.idMes,
      idUsuariosXevaluacion: criteriosEvaluacion.idUsuariosXevaluacion,
      idEstado: criteriosEvaluacion.idEstado,
      idVerificador: criteriosEvaluacion.idVerificador,
      idCriteriosEvaluacionXItem: criteriosEvaluacion.idCriteriosEvaluacionXItem,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const criteriosEvaluacion = this.createFromForm();
    if (criteriosEvaluacion.id !== undefined) {
      this.subscribeToSaveResponse(this.criteriosEvaluacionService.update(criteriosEvaluacion));
    } else {
      this.subscribeToSaveResponse(this.criteriosEvaluacionService.create(criteriosEvaluacion));
    }
  }

  private createFromForm(): ICriteriosEvaluacion {
    return {
      ...new CriteriosEvaluacion(),
      id: this.editForm.get(['id'])!.value,
      idCriteriosEvaluacion: this.editForm.get(['idCriteriosEvaluacion'])!.value,
      ceNombre: this.editForm.get(['ceNombre'])!.value,
      ceFechaCreacion: this.editForm.get(['ceFechaCreacion'])!.value,
      ceFechaModificacion: this.editForm.get(['ceFechaModificacion'])!.value,
      idAutoevalucionXproceso: this.editForm.get(['idAutoevalucionXproceso'])!.value,
      idAutoevalucion: this.editForm.get(['idAutoevalucion'])!.value,
      idMes: this.editForm.get(['idMes'])!.value,
      idUsuariosXevaluacion: this.editForm.get(['idUsuariosXevaluacion'])!.value,
      idEstado: this.editForm.get(['idEstado'])!.value,
      idVerificador: this.editForm.get(['idVerificador'])!.value,
      idCriteriosEvaluacionXItem: this.editForm.get(['idCriteriosEvaluacionXItem'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICriteriosEvaluacion>>): void {
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
