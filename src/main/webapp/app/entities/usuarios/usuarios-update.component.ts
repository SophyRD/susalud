import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IUsuarios, Usuarios } from 'app/shared/model/usuarios.model';
import { UsuariosService } from './usuarios.service';

@Component({
  selector: 'jhi-usuarios-update',
  templateUrl: './usuarios-update.component.html',
})
export class UsuariosUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    idUsuario: [null, [Validators.required]],
    usUsuario: [null, [Validators.required]],
    usContrasena: [null, [Validators.required]],
    usuariosIdUsuarios: [null, [Validators.required]],
    usuariosPerfilIdPerfil: [null, [Validators.required]],
  });

  constructor(protected usuariosService: UsuariosService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ usuarios }) => {
      this.updateForm(usuarios);
    });
  }

  updateForm(usuarios: IUsuarios): void {
    this.editForm.patchValue({
      id: usuarios.id,
      idUsuario: usuarios.idUsuario,
      usUsuario: usuarios.usUsuario,
      usContrasena: usuarios.usContrasena,
      usuariosIdUsuarios: usuarios.usuariosIdUsuarios,
      usuariosPerfilIdPerfil: usuarios.usuariosPerfilIdPerfil,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const usuarios = this.createFromForm();
    if (usuarios.id !== undefined) {
      this.subscribeToSaveResponse(this.usuariosService.update(usuarios));
    } else {
      this.subscribeToSaveResponse(this.usuariosService.create(usuarios));
    }
  }

  private createFromForm(): IUsuarios {
    return {
      ...new Usuarios(),
      id: this.editForm.get(['id'])!.value,
      idUsuario: this.editForm.get(['idUsuario'])!.value,
      usUsuario: this.editForm.get(['usUsuario'])!.value,
      usContrasena: this.editForm.get(['usContrasena'])!.value,
      usuariosIdUsuarios: this.editForm.get(['usuariosIdUsuarios'])!.value,
      usuariosPerfilIdPerfil: this.editForm.get(['usuariosPerfilIdPerfil'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IUsuarios>>): void {
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
