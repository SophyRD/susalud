import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAutoevalucionXproceso, AutoevalucionXproceso } from 'app/shared/model/autoevalucion-xproceso.model';
import { AutoevalucionXprocesoService } from './autoevalucion-xproceso.service';

@Component({
  selector: 'jhi-autoevalucion-xproceso-update',
  templateUrl: './autoevalucion-xproceso-update.component.html',
})
export class AutoevalucionXprocesoUpdateComponent implements OnInit {
  isSaving = false;
  apFechaCreacionDp: any;
  spFechaModificacionDp: any;

  editForm = this.fb.group({
    id: [],
    idAutoevalucionXproceso: [null, [Validators.required]],
    apFechaCreacion: [null, [Validators.required]],
    spFechaModificacion: [null, [Validators.required]],
    apComentario: [null, [Validators.required]],
    apPuntuacion: [null, [Validators.required]],
    idAutoevalucion: [null, [Validators.required]],
    idMes: [null, [Validators.required]],
    idUsuariosXevaluacion: [null, [Validators.required]],
    idEstado: [null, [Validators.required]],
    idVerificador: [null, [Validators.required]],
  });

  constructor(
    protected autoevalucionXprocesoService: AutoevalucionXprocesoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autoevalucionXproceso }) => {
      this.updateForm(autoevalucionXproceso);
    });
  }

  updateForm(autoevalucionXproceso: IAutoevalucionXproceso): void {
    this.editForm.patchValue({
      id: autoevalucionXproceso.id,
      idAutoevalucionXproceso: autoevalucionXproceso.idAutoevalucionXproceso,
      apFechaCreacion: autoevalucionXproceso.apFechaCreacion,
      spFechaModificacion: autoevalucionXproceso.spFechaModificacion,
      apComentario: autoevalucionXproceso.apComentario,
      apPuntuacion: autoevalucionXproceso.apPuntuacion,
      idAutoevalucion: autoevalucionXproceso.idAutoevalucion,
      idMes: autoevalucionXproceso.idMes,
      idUsuariosXevaluacion: autoevalucionXproceso.idUsuariosXevaluacion,
      idEstado: autoevalucionXproceso.idEstado,
      idVerificador: autoevalucionXproceso.idVerificador,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autoevalucionXproceso = this.createFromForm();
    if (autoevalucionXproceso.id !== undefined) {
      this.subscribeToSaveResponse(this.autoevalucionXprocesoService.update(autoevalucionXproceso));
    } else {
      this.subscribeToSaveResponse(this.autoevalucionXprocesoService.create(autoevalucionXproceso));
    }
  }

  private createFromForm(): IAutoevalucionXproceso {
    return {
      ...new AutoevalucionXproceso(),
      id: this.editForm.get(['id'])!.value,
      idAutoevalucionXproceso: this.editForm.get(['idAutoevalucionXproceso'])!.value,
      apFechaCreacion: this.editForm.get(['apFechaCreacion'])!.value,
      spFechaModificacion: this.editForm.get(['spFechaModificacion'])!.value,
      apComentario: this.editForm.get(['apComentario'])!.value,
      apPuntuacion: this.editForm.get(['apPuntuacion'])!.value,
      idAutoevalucion: this.editForm.get(['idAutoevalucion'])!.value,
      idMes: this.editForm.get(['idMes'])!.value,
      idUsuariosXevaluacion: this.editForm.get(['idUsuariosXevaluacion'])!.value,
      idEstado: this.editForm.get(['idEstado'])!.value,
      idVerificador: this.editForm.get(['idVerificador'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutoevalucionXproceso>>): void {
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
