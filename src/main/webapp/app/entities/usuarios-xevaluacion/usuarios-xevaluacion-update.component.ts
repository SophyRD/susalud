import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUsuariosXevaluacion, UsuariosXevaluacion } from 'app/shared/model/usuarios-xevaluacion.model';
import { UsuariosXevaluacionService } from './usuarios-xevaluacion.service';

@Component({
  selector: 'jhi-usuarios-xevaluacion-update',
  templateUrl: './usuarios-xevaluacion-update.component.html',
})
export class UsuariosXevaluacionUpdateComponent implements OnInit {
  isSaving = false;
  uFechaModificacionDp: any;
  uFechaCreacionDp: any;

  editForm = this.fb.group({
    id: [],
    idUsuariosXevaluacion: [null, [Validators.required]],
    uFechaModificacion: [null, [Validators.required]],
    uFechaCreacion: [null, [Validators.required]],
  });

  constructor(
    protected usuariosXevaluacionService: UsuariosXevaluacionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ usuariosXevaluacion }) => {
      this.updateForm(usuariosXevaluacion);
    });
  }

  updateForm(usuariosXevaluacion: IUsuariosXevaluacion): void {
    this.editForm.patchValue({
      id: usuariosXevaluacion.id,
      idUsuariosXevaluacion: usuariosXevaluacion.idUsuariosXevaluacion,
      uFechaModificacion: usuariosXevaluacion.uFechaModificacion,
      uFechaCreacion: usuariosXevaluacion.uFechaCreacion,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const usuariosXevaluacion = this.createFromForm();
    if (usuariosXevaluacion.id !== undefined) {
      this.subscribeToSaveResponse(this.usuariosXevaluacionService.update(usuariosXevaluacion));
    } else {
      this.subscribeToSaveResponse(this.usuariosXevaluacionService.create(usuariosXevaluacion));
    }
  }

  private createFromForm(): IUsuariosXevaluacion {
    return {
      ...new UsuariosXevaluacion(),
      id: this.editForm.get(['id'])!.value,
      idUsuariosXevaluacion: this.editForm.get(['idUsuariosXevaluacion'])!.value,
      uFechaModificacion: this.editForm.get(['uFechaModificacion'])!.value,
      uFechaCreacion: this.editForm.get(['uFechaCreacion'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUsuariosXevaluacion>>): void {
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
