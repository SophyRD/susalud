import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISubproceso, Subproceso } from 'app/shared/model/subproceso.model';
import { SubprocesoService } from './subproceso.service';

@Component({
  selector: 'jhi-subproceso-update',
  templateUrl: './subproceso-update.component.html',
})
export class SubprocesoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idSubproceso: [null, [Validators.required]],
    spDescripcion: [null, [Validators.required]],
    idProceso: [null, [Validators.required]],
    idMacroproceso: [null, [Validators.required]],
    idVerificador: [null, [Validators.required]],
  });

  constructor(protected subprocesoService: SubprocesoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subproceso }) => {
      this.updateForm(subproceso);
    });
  }

  updateForm(subproceso: ISubproceso): void {
    this.editForm.patchValue({
      id: subproceso.id,
      idSubproceso: subproceso.idSubproceso,
      spDescripcion: subproceso.spDescripcion,
      idProceso: subproceso.idProceso,
      idMacroproceso: subproceso.idMacroproceso,
      idVerificador: subproceso.idVerificador,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subproceso = this.createFromForm();
    if (subproceso.id !== undefined) {
      this.subscribeToSaveResponse(this.subprocesoService.update(subproceso));
    } else {
      this.subscribeToSaveResponse(this.subprocesoService.create(subproceso));
    }
  }

  private createFromForm(): ISubproceso {
    return {
      ...new Subproceso(),
      id: this.editForm.get(['id'])!.value,
      idSubproceso: this.editForm.get(['idSubproceso'])!.value,
      spDescripcion: this.editForm.get(['spDescripcion'])!.value,
      idProceso: this.editForm.get(['idProceso'])!.value,
      idMacroproceso: this.editForm.get(['idMacroproceso'])!.value,
      idVerificador: this.editForm.get(['idVerificador'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubproceso>>): void {
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
