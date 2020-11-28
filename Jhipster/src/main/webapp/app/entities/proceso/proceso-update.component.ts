import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProceso, Proceso } from 'app/shared/model/proceso.model';
import { ProcesoService } from './proceso.service';
import { IMacroproceso } from 'app/shared/model/macroproceso.model';
import { MacroprocesoService } from 'app/entities/macroproceso/macroproceso.service';

@Component({
  selector: 'jhi-proceso-update',
  templateUrl: './proceso-update.component.html',
})
export class ProcesoUpdateComponent implements OnInit {
  isSaving = false;
  macroprocesos: IMacroproceso[] = [];

  editForm = this.fb.group({
    id: [],
    descripcion: [null, [Validators.required]],
    macroproceso: [],
  });

  constructor(
    protected procesoService: ProcesoService,
    protected macroprocesoService: MacroprocesoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ proceso }) => {
      this.updateForm(proceso);

      this.macroprocesoService.query().subscribe((res: HttpResponse<IMacroproceso[]>) => (this.macroprocesos = res.body || []));
    });
  }

  updateForm(proceso: IProceso): void {
    this.editForm.patchValue({
      id: proceso.id,
      descripcion: proceso.descripcion,
      macroproceso: proceso.macroproceso,
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
      descripcion: this.editForm.get(['descripcion'])!.value,
      macroproceso: this.editForm.get(['macroproceso'])!.value,
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

  trackById(index: number, item: IMacroproceso): any {
    return item.id;
  }
}
