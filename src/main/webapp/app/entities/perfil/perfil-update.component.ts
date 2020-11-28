import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPerfil, Perfil } from 'app/shared/model/perfil.model';
import { PerfilService } from './perfil.service';

@Component({
  selector: 'jhi-perfil-update',
  templateUrl: './perfil-update.component.html',
})
export class PerfilUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idPerfil: [null, [Validators.required]],
    peDescripcion: [null, [Validators.required]],
  });

  constructor(protected perfilService: PerfilService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ perfil }) => {
      this.updateForm(perfil);
    });
  }

  updateForm(perfil: IPerfil): void {
    this.editForm.patchValue({
      id: perfil.id,
      idPerfil: perfil.idPerfil,
      peDescripcion: perfil.peDescripcion,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const perfil = this.createFromForm();
    if (perfil.id !== undefined) {
      this.subscribeToSaveResponse(this.perfilService.update(perfil));
    } else {
      this.subscribeToSaveResponse(this.perfilService.create(perfil));
    }
  }

  private createFromForm(): IPerfil {
    return {
      ...new Perfil(),
      id: this.editForm.get(['id'])!.value,
      idPerfil: this.editForm.get(['idPerfil'])!.value,
      peDescripcion: this.editForm.get(['peDescripcion'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPerfil>>): void {
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
