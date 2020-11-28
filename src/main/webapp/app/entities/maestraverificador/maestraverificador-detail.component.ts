import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMaestraverificador } from 'app/shared/model/maestraverificador.model';

@Component({
  selector: 'jhi-maestraverificador-detail',
  templateUrl: './maestraverificador-detail.component.html',
})
export class MaestraverificadorDetailComponent implements OnInit {
  maestraverificador: IMaestraverificador | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ maestraverificador }) => (this.maestraverificador = maestraverificador));
  }

  previousState(): void {
    window.history.back();
  }
}
