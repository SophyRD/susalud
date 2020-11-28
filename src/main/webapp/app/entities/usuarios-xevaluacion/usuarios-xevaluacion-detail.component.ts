import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUsuariosXevaluacion } from 'app/shared/model/usuarios-xevaluacion.model';

@Component({
  selector: 'jhi-usuarios-xevaluacion-detail',
  templateUrl: './usuarios-xevaluacion-detail.component.html',
})
export class UsuariosXevaluacionDetailComponent implements OnInit {
  usuariosXevaluacion: IUsuariosXevaluacion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ usuariosXevaluacion }) => (this.usuariosXevaluacion = usuariosXevaluacion));
  }

  previousState(): void {
    window.history.back();
  }
}
