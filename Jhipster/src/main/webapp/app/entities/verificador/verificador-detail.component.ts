import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVerificador } from 'app/shared/model/verificador.model';

@Component({
  selector: 'jhi-verificador-detail',
  templateUrl: './verificador-detail.component.html',
})
export class VerificadorDetailComponent implements OnInit {
  verificador: IVerificador | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ verificador }) => (this.verificador = verificador));
  }

  previousState(): void {
    window.history.back();
  }
}
