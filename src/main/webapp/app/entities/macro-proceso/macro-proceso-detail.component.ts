import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMacroProceso } from 'app/shared/model/macro-proceso.model';

@Component({
  selector: 'jhi-macro-proceso-detail',
  templateUrl: './macro-proceso-detail.component.html',
})
export class MacroProcesoDetailComponent implements OnInit {
  macroProceso: IMacroProceso | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ macroProceso }) => (this.macroProceso = macroProceso));
  }

  previousState(): void {
    window.history.back();
  }
}
