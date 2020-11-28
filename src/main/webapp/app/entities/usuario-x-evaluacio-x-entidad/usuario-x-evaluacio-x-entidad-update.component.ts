import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUsuarioXEvaluacioXEntidad, UsuarioXEvaluacioXEntidad } from 'app/shared/model/usuario-x-evaluacio-x-entidad.model';
import { UsuarioXEvaluacioXEntidadService } from './usuario-x-evaluacio-x-entidad.service';

@Component({
  selector: 'jhi-usuario-x-evaluacio-x-entidad-update',
  templateUrl: './usuario-x-evaluacio-x-entidad-update.component.html',
})
export class UsuarioXEvaluacioXEntidadUpdateComponent implements OnInit {
  isSaving = false;
  ueeFechaCreacionDp: any;
  ueeFechaModificacionDp: any;

  editForm = this.fb.group({
    id: [],
    idUsuarioXEvaluacioXEntidad: [null, [Validators.required]],
    ueeFechaCreacion: [null, [Validators.required]],
    ueeFechaModificacion: [null, [Validators.required]],
    idUsuariosXevaluacion: [null, [Validators.required]],
  });

  constructor(
    protected usuarioXEvaluacioXEntidadService: UsuarioXEvaluacioXEntidadService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ usuarioXEvaluacioXEntidad }) => {
      this.updateForm(usuarioXEvaluacioXEntidad);
    });
  }

  updateForm(usuarioXEvaluacioXEntidad: IUsuarioXEvaluacioXEntidad): void {
    this.editForm.patchValue({
      id: usuarioXEvaluacioXEntidad.id,
      idUsuarioXEvaluacioXEntidad: usuarioXEvaluacioXEntidad.idUsuarioXEvaluacioXEntidad,
      ueeFechaCreacion: usuarioXEvaluacioXEntidad.ueeFechaCreacion,
      ueeFechaModificacion: usuarioXEvaluacioXEntidad.ueeFechaModificacion,
      idUsuariosXevaluacion: usuarioXEvaluacioXEntidad.idUsuariosXevaluacion,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const usuarioXEvaluacioXEntidad = this.createFromForm();
    if (usuarioXEvaluacioXEntidad.id !== undefined) {
      this.subscribeToSaveResponse(this.usuarioXEvaluacioXEntidadService.update(usuarioXEvaluacioXEntidad));
    } else {
      this.subscribeToSaveResponse(this.usuarioXEvaluacioXEntidadService.create(usuarioXEvaluacioXEntidad));
    }
  }

  private createFromForm(): IUsuarioXEvaluacioXEntidad {
    return {
      ...new UsuarioXEvaluacioXEntidad(),
      id: this.editForm.get(['id'])!.value,
      idUsuarioXEvaluacioXEntidad: this.editForm.get(['idUsuarioXEvaluacioXEntidad'])!.value,
      ueeFechaCreacion: this.editForm.get(['ueeFechaCreacion'])!.value,
      ueeFechaModificacion: this.editForm.get(['ueeFechaModificacion'])!.value,
      idUsuariosXevaluacion: this.editForm.get(['idUsuariosXevaluacion'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUsuarioXEvaluacioXEntidad>>): void {
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
