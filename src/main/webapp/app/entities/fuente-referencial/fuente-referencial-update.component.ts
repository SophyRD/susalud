import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IFuenteReferencial, FuenteReferencial } from 'app/shared/model/fuente-referencial.model';
import { FuenteReferencialService } from './fuente-referencial.service';

@Component({
  selector: 'jhi-fuente-referencial-update',
  templateUrl: './fuente-referencial-update.component.html',
})
export class FuenteReferencialUpdateComponent implements OnInit {
  isSaving = false;
  frFechaCreacionDp: any;
  frFechaModificacionDp: any;

  editForm = this.fb.group({
    id: [],
    idFuenteReferencial: [null, [Validators.required]],
    frNombre: [null, [Validators.required]],
    frFechaCreacion: [null, [Validators.required]],
    frFechaModificacion: [null, [Validators.required]],
    idAutoevalucionXproceso: [null, [Validators.required]],
    idAutoevalucion: [null, [Validators.required]],
    idMes: [null, [Validators.required]],
    idUsuariosXevaluacion: [null, [Validators.required]],
    idEstado: [null, [Validators.required]],
    idVerificador: [null, [Validators.required]],
    idFuenteReferenciaXItem: [null, [Validators.required]],
  });

  constructor(
    protected fuenteReferencialService: FuenteReferencialService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fuenteReferencial }) => {
      this.updateForm(fuenteReferencial);
    });
  }

  updateForm(fuenteReferencial: IFuenteReferencial): void {
    this.editForm.patchValue({
      id: fuenteReferencial.id,
      idFuenteReferencial: fuenteReferencial.idFuenteReferencial,
      frNombre: fuenteReferencial.frNombre,
      frFechaCreacion: fuenteReferencial.frFechaCreacion,
      frFechaModificacion: fuenteReferencial.frFechaModificacion,
      idAutoevalucionXproceso: fuenteReferencial.idAutoevalucionXproceso,
      idAutoevalucion: fuenteReferencial.idAutoevalucion,
      idMes: fuenteReferencial.idMes,
      idUsuariosXevaluacion: fuenteReferencial.idUsuariosXevaluacion,
      idEstado: fuenteReferencial.idEstado,
      idVerificador: fuenteReferencial.idVerificador,
      idFuenteReferenciaXItem: fuenteReferencial.idFuenteReferenciaXItem,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fuenteReferencial = this.createFromForm();
    if (fuenteReferencial.id !== undefined) {
      this.subscribeToSaveResponse(this.fuenteReferencialService.update(fuenteReferencial));
    } else {
      this.subscribeToSaveResponse(this.fuenteReferencialService.create(fuenteReferencial));
    }
  }

  private createFromForm(): IFuenteReferencial {
    return {
      ...new FuenteReferencial(),
      id: this.editForm.get(['id'])!.value,
      idFuenteReferencial: this.editForm.get(['idFuenteReferencial'])!.value,
      frNombre: this.editForm.get(['frNombre'])!.value,
      frFechaCreacion: this.editForm.get(['frFechaCreacion'])!.value,
      frFechaModificacion: this.editForm.get(['frFechaModificacion'])!.value,
      idAutoevalucionXproceso: this.editForm.get(['idAutoevalucionXproceso'])!.value,
      idAutoevalucion: this.editForm.get(['idAutoevalucion'])!.value,
      idMes: this.editForm.get(['idMes'])!.value,
      idUsuariosXevaluacion: this.editForm.get(['idUsuariosXevaluacion'])!.value,
      idEstado: this.editForm.get(['idEstado'])!.value,
      idVerificador: this.editForm.get(['idVerificador'])!.value,
      idFuenteReferenciaXItem: this.editForm.get(['idFuenteReferenciaXItem'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFuenteReferencial>>): void {
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
