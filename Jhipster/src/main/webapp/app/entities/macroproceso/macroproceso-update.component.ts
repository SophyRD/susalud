import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMacroproceso, Macroproceso } from 'app/shared/model/macroproceso.model';
import { MacroprocesoService } from './macroproceso.service';

@Component({
  selector: 'jhi-macroproceso-update',
  templateUrl: './macroproceso-update.component.html',
})
export class MacroprocesoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    descripcion: [null, [Validators.required]],
  });

  constructor(protected macroprocesoService: MacroprocesoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ macroproceso }) => {
      this.updateForm(macroproceso);
    });
  }

  updateForm(macroproceso: IMacroproceso): void {
    this.editForm.patchValue({
      id: macroproceso.id,
      descripcion: macroproceso.descripcion,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const macroproceso = this.createFromForm();
    if (macroproceso.id !== undefined) {
      this.subscribeToSaveResponse(this.macroprocesoService.update(macroproceso));
    } else {
      this.subscribeToSaveResponse(this.macroprocesoService.create(macroproceso));
    }
  }

  private createFromForm(): IMacroproceso {
    return {
      ...new Macroproceso(),
      id: this.editForm.get(['id'])!.value,
      descripcion: this.editForm.get(['descripcion'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMacroproceso>>): void {
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
