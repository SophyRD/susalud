import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEstado, Estado } from 'app/shared/model/estado.model';
import { EstadoService } from './estado.service';

@Component({
  selector: 'jhi-estado-update',
  templateUrl: './estado-update.component.html',
})
export class EstadoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idEstado: [null, [Validators.required]],
    eEstado: [null, [Validators.required]],
    idVerificador: [null, [Validators.required]],
  });

  constructor(protected estadoService: EstadoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ estado }) => {
      this.updateForm(estado);
    });
  }

  updateForm(estado: IEstado): void {
    this.editForm.patchValue({
      id: estado.id,
      idEstado: estado.idEstado,
      eEstado: estado.eEstado,
      idVerificador: estado.idVerificador,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const estado = this.createFromForm();
    if (estado.id !== undefined) {
      this.subscribeToSaveResponse(this.estadoService.update(estado));
    } else {
      this.subscribeToSaveResponse(this.estadoService.create(estado));
    }
  }

  private createFromForm(): IEstado {
    return {
      ...new Estado(),
      id: this.editForm.get(['id'])!.value,
      idEstado: this.editForm.get(['idEstado'])!.value,
      eEstado: this.editForm.get(['eEstado'])!.value,
      idVerificador: this.editForm.get(['idVerificador'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEstado>>): void {
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
