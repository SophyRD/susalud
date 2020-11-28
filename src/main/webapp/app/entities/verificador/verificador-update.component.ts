import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVerificador, Verificador } from 'app/shared/model/verificador.model';
import { VerificadorService } from './verificador.service';

@Component({
  selector: 'jhi-verificador-update',
  templateUrl: './verificador-update.component.html',
})
export class VerificadorUpdateComponent implements OnInit {
  isSaving = false;
  v1FechaCreacionDp: any;
  v1FechaModificacionDp: any;

  editForm = this.fb.group({
    id: [],
    idVerificador: [null, [Validators.required]],
    v1FechaCreacion: [null, [Validators.required]],
    v1FechaModificacion: [null, [Validators.required]],
  });

  constructor(protected verificadorService: VerificadorService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ verificador }) => {
      this.updateForm(verificador);
    });
  }

  updateForm(verificador: IVerificador): void {
    this.editForm.patchValue({
      id: verificador.id,
      idVerificador: verificador.idVerificador,
      v1FechaCreacion: verificador.v1FechaCreacion,
      v1FechaModificacion: verificador.v1FechaModificacion,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const verificador = this.createFromForm();
    if (verificador.id !== undefined) {
      this.subscribeToSaveResponse(this.verificadorService.update(verificador));
    } else {
      this.subscribeToSaveResponse(this.verificadorService.create(verificador));
    }
  }

  private createFromForm(): IVerificador {
    return {
      ...new Verificador(),
      id: this.editForm.get(['id'])!.value,
      idVerificador: this.editForm.get(['idVerificador'])!.value,
      v1FechaCreacion: this.editForm.get(['v1FechaCreacion'])!.value,
      v1FechaModificacion: this.editForm.get(['v1FechaModificacion'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVerificador>>): void {
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
