import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUsuarioXEvaluacioXEntidad } from 'app/shared/model/usuario-x-evaluacio-x-entidad.model';

@Component({
  selector: 'jhi-usuario-x-evaluacio-x-entidad-detail',
  templateUrl: './usuario-x-evaluacio-x-entidad-detail.component.html',
})
export class UsuarioXEvaluacioXEntidadDetailComponent implements OnInit {
  usuarioXEvaluacioXEntidad: IUsuarioXEvaluacioXEntidad | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ usuarioXEvaluacioXEntidad }) => (this.usuarioXEvaluacioXEntidad = usuarioXEvaluacioXEntidad));
  }

  previousState(): void {
    window.history.back();
  }
}
