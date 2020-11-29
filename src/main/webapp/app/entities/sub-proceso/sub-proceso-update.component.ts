import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISubProceso, SubProceso } from 'app/shared/model/sub-proceso.model';
import { SubProcesoService } from './sub-proceso.service';
import { IProceso } from 'app/shared/model/proceso.model';
import { ProcesoService } from 'app/entities/proceso/proceso.service';

@Component({
  selector: 'jhi-sub-proceso-update',
  templateUrl: './sub-proceso-update.component.html',
})
export class SubProcesoUpdateComponent implements OnInit {
  isSaving = false;
  procesos: IProceso[] = [];

  editForm = this.fb.group({
    id: [],
    idSubproceso: [],
    spDescripcion: [],
    procesoIdProceso: [],
    procesoMacroprocesoIdMacroproceso: [],
    procesoMacroprocesoVerificadorIdVerificador: [],
    proceso: [],
  });

  constructor(
    protected subProcesoService: SubProcesoService,
    protected procesoService: ProcesoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subProceso }) => {
      this.updateForm(subProceso);

      this.procesoService.query().subscribe((res: HttpResponse<IProceso[]>) => (this.procesos = res.body || []));
    });
  }

  updateForm(subProceso: ISubProceso): void {
    this.editForm.patchValue({
      id: subProceso.id,
      idSubproceso: subProceso.idSubproceso,
      spDescripcion: subProceso.spDescripcion,
      procesoIdProceso: subProceso.procesoIdProceso,
      procesoMacroprocesoIdMacroproceso: subProceso.procesoMacroprocesoIdMacroproceso,
      procesoMacroprocesoVerificadorIdVerificador: subProceso.procesoMacroprocesoVerificadorIdVerificador,
      proceso: subProceso.proceso,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const subProceso = this.createFromForm();
    if (subProceso.id !== undefined) {
      this.subscribeToSaveResponse(this.subProcesoService.update(subProceso));
    } else {
      this.subscribeToSaveResponse(this.subProcesoService.create(subProceso));
    }
  }

  private createFromForm(): ISubProceso {
    return {
      ...new SubProceso(),
      id: this.editForm.get(['id'])!.value,
      idSubproceso: this.editForm.get(['idSubproceso'])!.value,
      spDescripcion: this.editForm.get(['spDescripcion'])!.value,
      procesoIdProceso: this.editForm.get(['procesoIdProceso'])!.value,
      procesoMacroprocesoIdMacroproceso: this.editForm.get(['procesoMacroprocesoIdMacroproceso'])!.value,
      procesoMacroprocesoVerificadorIdVerificador: this.editForm.get(['procesoMacroprocesoVerificadorIdVerificador'])!.value,
      proceso: this.editForm.get(['proceso'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISubProceso>>): void {
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

  trackById(index: number, item: IProceso): any {
    return item.id;
  }
}
