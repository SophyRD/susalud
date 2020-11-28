import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProceso, Proceso } from 'app/shared/model/proceso.model';
import { ProcesoService } from './proceso.service';

@Component({
  selector: 'jhi-proceso-update',
  templateUrl: './proceso-update.component.html',
})
export class ProcesoUpdateComponent implements OnInit {
  isSaving = false;
  idMacroprocesoDp: any;

  editForm = this.fb.group({
    id: [],
    idProceso: [null, [Validators.required]],
    prDescripcion: [null, [Validators.required]],
    idMacroproceso: [null, [Validators.required]],
    idVerificador: [null, [Validators.required]],
  });

  constructor(protected procesoService: ProcesoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proceso }) => {
      this.updateForm(proceso);
    });
  }

  updateForm(proceso: IProceso): void {
    this.editForm.patchValue({
      id: proceso.id,
      idProceso: proceso.idProceso,
      prDescripcion: proceso.prDescripcion,
      idMacroproceso: proceso.idMacroproceso,
      idVerificador: proceso.idVerificador,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const proceso = this.createFromForm();
    if (proceso.id !== undefined) {
      this.subscribeToSaveResponse(this.procesoService.update(proceso));
    } else {
      this.subscribeToSaveResponse(this.procesoService.create(proceso));
    }
  }

  private createFromForm(): IProceso {
    return {
      ...new Proceso(),
      id: this.editForm.get(['id'])!.value,
      idProceso: this.editForm.get(['idProceso'])!.value,
      prDescripcion: this.editForm.get(['prDescripcion'])!.value,
      idMacroproceso: this.editForm.get(['idMacroproceso'])!.value,
      idVerificador: this.editForm.get(['idVerificador'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProceso>>): void {
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
