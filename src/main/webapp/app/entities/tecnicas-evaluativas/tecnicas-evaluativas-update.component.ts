import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITecnicasEvaluativas, TecnicasEvaluativas } from 'app/shared/model/tecnicas-evaluativas.model';
import { TecnicasEvaluativasService } from './tecnicas-evaluativas.service';

@Component({
  selector: 'jhi-tecnicas-evaluativas-update',
  templateUrl: './tecnicas-evaluativas-update.component.html',
})
export class TecnicasEvaluativasUpdateComponent implements OnInit {
  isSaving = false;
  teFechaCreacionDp: any;
  teFechaModificacionDp: any;

  editForm = this.fb.group({
    id: [],
    idTecnicasEvaluativas: [null, [Validators.required]],
    teNombre: [null, [Validators.required]],
    teFechaCreacion: [null, [Validators.required]],
    teFechaModificacion: [null, [Validators.required]],
    idAutoevalucionXproceso: [null, [Validators.required]],
    idAutoevalucion: [null, [Validators.required]],
    idMes: [null, [Validators.required]],
    idUsuariosXevaluacion: [null, [Validators.required]],
    idEstado: [null, [Validators.required]],
    idVerificador: [null, [Validators.required]],
    idTecnicasEvaluativasXItem: [null, [Validators.required]],
  });

  constructor(
    protected tecnicasEvaluativasService: TecnicasEvaluativasService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tecnicasEvaluativas }) => {
      this.updateForm(tecnicasEvaluativas);
    });
  }

  updateForm(tecnicasEvaluativas: ITecnicasEvaluativas): void {
    this.editForm.patchValue({
      id: tecnicasEvaluativas.id,
      idTecnicasEvaluativas: tecnicasEvaluativas.idTecnicasEvaluativas,
      teNombre: tecnicasEvaluativas.teNombre,
      teFechaCreacion: tecnicasEvaluativas.teFechaCreacion,
      teFechaModificacion: tecnicasEvaluativas.teFechaModificacion,
      idAutoevalucionXproceso: tecnicasEvaluativas.idAutoevalucionXproceso,
      idAutoevalucion: tecnicasEvaluativas.idAutoevalucion,
      idMes: tecnicasEvaluativas.idMes,
      idUsuariosXevaluacion: tecnicasEvaluativas.idUsuariosXevaluacion,
      idEstado: tecnicasEvaluativas.idEstado,
      idVerificador: tecnicasEvaluativas.idVerificador,
      idTecnicasEvaluativasXItem: tecnicasEvaluativas.idTecnicasEvaluativasXItem,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tecnicasEvaluativas = this.createFromForm();
    if (tecnicasEvaluativas.id !== undefined) {
      this.subscribeToSaveResponse(this.tecnicasEvaluativasService.update(tecnicasEvaluativas));
    } else {
      this.subscribeToSaveResponse(this.tecnicasEvaluativasService.create(tecnicasEvaluativas));
    }
  }

  private createFromForm(): ITecnicasEvaluativas {
    return {
      ...new TecnicasEvaluativas(),
      id: this.editForm.get(['id'])!.value,
      idTecnicasEvaluativas: this.editForm.get(['idTecnicasEvaluativas'])!.value,
      teNombre: this.editForm.get(['teNombre'])!.value,
      teFechaCreacion: this.editForm.get(['teFechaCreacion'])!.value,
      teFechaModificacion: this.editForm.get(['teFechaModificacion'])!.value,
      idAutoevalucionXproceso: this.editForm.get(['idAutoevalucionXproceso'])!.value,
      idAutoevalucion: this.editForm.get(['idAutoevalucion'])!.value,
      idMes: this.editForm.get(['idMes'])!.value,
      idUsuariosXevaluacion: this.editForm.get(['idUsuariosXevaluacion'])!.value,
      idEstado: this.editForm.get(['idEstado'])!.value,
      idVerificador: this.editForm.get(['idVerificador'])!.value,
      idTecnicasEvaluativasXItem: this.editForm.get(['idTecnicasEvaluativasXItem'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITecnicasEvaluativas>>): void {
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
