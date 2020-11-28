import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAutoevalucion, Autoevalucion } from 'app/shared/model/autoevalucion.model';
import { AutoevalucionService } from './autoevalucion.service';

@Component({
  selector: 'jhi-autoevalucion-update',
  templateUrl: './autoevalucion-update.component.html',
})
export class AutoevalucionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idAutoevalucion: [null, [Validators.required]],
    aAvance: [null, [Validators.required]],
    idMes: [null, [Validators.required]],
    idUsuariosXevaluacion: [null, [Validators.required]],
    idEstado: [null, [Validators.required]],
  });

  constructor(protected autoevalucionService: AutoevalucionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autoevalucion }) => {
      this.updateForm(autoevalucion);
    });
  }

  updateForm(autoevalucion: IAutoevalucion): void {
    this.editForm.patchValue({
      id: autoevalucion.id,
      idAutoevalucion: autoevalucion.idAutoevalucion,
      aAvance: autoevalucion.aAvance,
      idMes: autoevalucion.idMes,
      idUsuariosXevaluacion: autoevalucion.idUsuariosXevaluacion,
      idEstado: autoevalucion.idEstado,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const autoevalucion = this.createFromForm();
    if (autoevalucion.id !== undefined) {
      this.subscribeToSaveResponse(this.autoevalucionService.update(autoevalucion));
    } else {
      this.subscribeToSaveResponse(this.autoevalucionService.create(autoevalucion));
    }
  }

  private createFromForm(): IAutoevalucion {
    return {
      ...new Autoevalucion(),
      id: this.editForm.get(['id'])!.value,
      idAutoevalucion: this.editForm.get(['idAutoevalucion'])!.value,
      aAvance: this.editForm.get(['aAvance'])!.value,
      idMes: this.editForm.get(['idMes'])!.value,
      idUsuariosXevaluacion: this.editForm.get(['idUsuariosXevaluacion'])!.value,
      idEstado: this.editForm.get(['idEstado'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAutoevalucion>>): void {
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
