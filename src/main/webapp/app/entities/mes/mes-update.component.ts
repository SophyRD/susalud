import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMes, Mes } from 'app/shared/model/mes.model';
import { MesService } from './mes.service';

@Component({
  selector: 'jhi-mes-update',
  templateUrl: './mes-update.component.html',
})
export class MesUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idMes: [null, [Validators.required]],
    mMes: [null, [Validators.required]],
    mAno: [null, [Validators.required]],
  });

  constructor(protected mesService: MesService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mes }) => {
      this.updateForm(mes);
    });
  }

  updateForm(mes: IMes): void {
    this.editForm.patchValue({
      id: mes.id,
      idMes: mes.idMes,
      mMes: mes.mMes,
      mAno: mes.mAno,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const mes = this.createFromForm();
    if (mes.id !== undefined) {
      this.subscribeToSaveResponse(this.mesService.update(mes));
    } else {
      this.subscribeToSaveResponse(this.mesService.create(mes));
    }
  }

  private createFromForm(): IMes {
    return {
      ...new Mes(),
      id: this.editForm.get(['id'])!.value,
      idMes: this.editForm.get(['idMes'])!.value,
      mMes: this.editForm.get(['mMes'])!.value,
      mAno: this.editForm.get(['mAno'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMes>>): void {
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
