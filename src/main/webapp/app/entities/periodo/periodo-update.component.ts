import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPeriodo, Periodo } from 'app/shared/model/periodo.model';
import { PeriodoService } from './periodo.service';

@Component({
  selector: 'jhi-periodo-update',
  templateUrl: './periodo-update.component.html',
})
export class PeriodoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idPeriodo: [null, [Validators.required]],
    pPeriodo: [null, [Validators.required]],
    mesIdMes: [null, [Validators.required]],
  });

  constructor(protected periodoService: PeriodoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ periodo }) => {
      this.updateForm(periodo);
    });
  }

  updateForm(periodo: IPeriodo): void {
    this.editForm.patchValue({
      id: periodo.id,
      idPeriodo: periodo.idPeriodo,
      pPeriodo: periodo.pPeriodo,
      mesIdMes: periodo.mesIdMes,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const periodo = this.createFromForm();
    if (periodo.id !== undefined) {
      this.subscribeToSaveResponse(this.periodoService.update(periodo));
    } else {
      this.subscribeToSaveResponse(this.periodoService.create(periodo));
    }
  }

  private createFromForm(): IPeriodo {
    return {
      ...new Periodo(),
      id: this.editForm.get(['id'])!.value,
      idPeriodo: this.editForm.get(['idPeriodo'])!.value,
      pPeriodo: this.editForm.get(['pPeriodo'])!.value,
      mesIdMes: this.editForm.get(['mesIdMes'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPeriodo>>): void {
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
