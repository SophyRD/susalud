import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProceso, Proceso } from 'app/shared/model/proceso.model';
import { ProcesoService } from './proceso.service';
import { IMacroProceso } from 'app/shared/model/macro-proceso.model';
import { MacroProcesoService } from 'app/entities/macro-proceso/macro-proceso.service';

@Component({
  selector: 'jhi-proceso-update',
  templateUrl: './proceso-update.component.html',
})
export class ProcesoUpdateComponent implements OnInit {
  isSaving = false;
  macroprocesos: IMacroProceso[] = [];

  editForm = this.fb.group({
    id: [],
    idProceso: [],
    prDescripcion: [],
    macroprocesoIdMacroproseso: [],
    macroprocesoVerificadorIdVerificador: [],
    macroProceso: [],
  });

  constructor(
    protected procesoService: ProcesoService,
    protected macroProcesoService: MacroProcesoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proceso }) => {
      this.updateForm(proceso);

      this.macroProcesoService.query().subscribe((res: HttpResponse<IMacroProceso[]>) => (this.macroprocesos = res.body || []));
    });
  }

  updateForm(proceso: IProceso): void {
    this.editForm.patchValue({
      id: proceso.id,
      idProceso: proceso.idProceso,
      prDescripcion: proceso.prDescripcion,
      macroprocesoIdMacroproseso: proceso.macroprocesoIdMacroproseso,
      macroprocesoVerificadorIdVerificador: proceso.macroprocesoVerificadorIdVerificador,
      macroProceso: proceso.macroProceso,
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
      macroprocesoIdMacroproseso: this.editForm.get(['macroprocesoIdMacroproseso'])!.value,
      macroprocesoVerificadorIdVerificador: this.editForm.get(['macroprocesoVerificadorIdVerificador'])!.value,
      macroProceso: this.editForm.get(['macroProceso'])!.value,
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

  trackById(index: number, item: IMacroProceso): any {
    return item.id;
  }
}
