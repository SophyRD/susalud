import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISubproceso, Subproceso } from 'app/shared/model/subproceso.model';
import { SubprocesoService } from './subproceso.service';
import { IProceso } from 'app/shared/model/proceso.model';
import { ProcesoService } from 'app/entities/proceso/proceso.service';

@Component({
  selector: 'jhi-subproceso-update',
  templateUrl: './subproceso-update.component.html',
})
export class SubprocesoUpdateComponent implements OnInit {
  isSaving = false;
  procesos: IProceso[] = [];

  editForm = this.fb.group({
    id: [],
    descripcion: [null, [Validators.required]],
    proceso: [],
  });

  constructor(
    protected subprocesoService: SubprocesoService,
    protected procesoService: ProcesoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subproceso }) => {
      this.updateForm(subproceso);

      this.procesoService.query().subscribe((res: HttpResponse<IProceso[]>) => (this.procesos = res.body || []));
    });
  }

  updateForm(subproceso: ISubproceso): void {
    this.editForm.patchValue({
      id: subproceso.id,
      descripcion: subproceso.descripcion,
      proceso: subproceso.proceso,
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
      descripcion: this.editForm.get(['descripcion'])!.value,
      proceso: this.editForm.get(['proceso'])!.value,
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

  trackById(index: number, item: IProceso): any {
    return item.id;
  }
}
