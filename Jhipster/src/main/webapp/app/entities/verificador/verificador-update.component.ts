import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IVerificador, Verificador } from 'app/shared/model/verificador.model';
import { VerificadorService } from './verificador.service';
import { IMacroproceso } from 'app/shared/model/macroproceso.model';
import { MacroprocesoService } from 'app/entities/macroproceso/macroproceso.service';
import { IProceso } from 'app/shared/model/proceso.model';
import { ProcesoService } from 'app/entities/proceso/proceso.service';
import { ISubproceso } from 'app/shared/model/subproceso.model';
import { SubprocesoService } from 'app/entities/subproceso/subproceso.service';

type SelectableEntity = IMacroproceso | IProceso | ISubproceso;

@Component({
  selector: 'jhi-verificador-update',
  templateUrl: './verificador-update.component.html',
})
export class VerificadorUpdateComponent implements OnInit {
  isSaving = false;
  macroprocesos: IMacroproceso[] = [];
  procesos: IProceso[] = [];
  subprocesos: ISubproceso[] = [];

  editForm = this.fb.group({
    id: [],
    codigo: [null, [Validators.required]],
    descripcion: [null, [Validators.required]],
    estado: [null, [Validators.required]],
    macroproceso: [],
    proceso: [],
    subproceso: [],
  });

  constructor(
    protected verificadorService: VerificadorService,
    protected macroprocesoService: MacroprocesoService,
    protected procesoService: ProcesoService,
    protected subprocesoService: SubprocesoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ verificador }) => {
      this.updateForm(verificador);

      this.macroprocesoService.query().subscribe((res: HttpResponse<IMacroproceso[]>) => (this.macroprocesos = res.body || []));

      this.procesoService.query().subscribe((res: HttpResponse<IProceso[]>) => (this.procesos = res.body || []));

      this.subprocesoService.query().subscribe((res: HttpResponse<ISubproceso[]>) => (this.subprocesos = res.body || []));
    });
  }

  updateForm(verificador: IVerificador): void {
    this.editForm.patchValue({
      id: verificador.id,
      codigo: verificador.codigo,
      descripcion: verificador.descripcion,
      estado: verificador.estado,
      macroproceso: verificador.macroproceso,
      proceso: verificador.proceso,
      subproceso: verificador.subproceso,
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
      codigo: this.editForm.get(['codigo'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value,
      estado: this.editForm.get(['estado'])!.value,
      macroproceso: this.editForm.get(['macroproceso'])!.value,
      proceso: this.editForm.get(['proceso'])!.value,
      subproceso: this.editForm.get(['subproceso'])!.value,
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
