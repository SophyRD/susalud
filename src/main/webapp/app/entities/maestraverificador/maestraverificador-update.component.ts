import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMaestraverificador, Maestraverificador } from 'app/shared/model/maestraverificador.model';
import { MaestraverificadorService } from './maestraverificador.service';

@Component({
  selector: 'jhi-maestraverificador-update',
  templateUrl: './maestraverificador-update.component.html',
})
export class MaestraverificadorUpdateComponent implements OnInit {
  isSaving = false;
  mvFechaDp: any;
  mvFechaModificacionDp: any;

  editForm = this.fb.group({
    id: [],
    idMaestraverificador: [null, [Validators.required]],
    mvFecha: [null, [Validators.required]],
    mvFechaModificacion: [null, [Validators.required]],
    idVerificador: [null, [Validators.required]],
  });

  constructor(
    protected maestraverificadorService: MaestraverificadorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ maestraverificador }) => {
      this.updateForm(maestraverificador);
    });
  }

  updateForm(maestraverificador: IMaestraverificador): void {
    this.editForm.patchValue({
      id: maestraverificador.id,
      idMaestraverificador: maestraverificador.idMaestraverificador,
      mvFecha: maestraverificador.mvFecha,
      mvFechaModificacion: maestraverificador.mvFechaModificacion,
      idVerificador: maestraverificador.idVerificador,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const maestraverificador = this.createFromForm();
    if (maestraverificador.id !== undefined) {
      this.subscribeToSaveResponse(this.maestraverificadorService.update(maestraverificador));
    } else {
      this.subscribeToSaveResponse(this.maestraverificadorService.create(maestraverificador));
    }
  }

  private createFromForm(): IMaestraverificador {
    return {
      ...new Maestraverificador(),
      id: this.editForm.get(['id'])!.value,
      idMaestraverificador: this.editForm.get(['idMaestraverificador'])!.value,
      mvFecha: this.editForm.get(['mvFecha'])!.value,
      mvFechaModificacion: this.editForm.get(['mvFechaModificacion'])!.value,
      idVerificador: this.editForm.get(['idVerificador'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMaestraverificador>>): void {
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
