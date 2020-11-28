import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMacroproceso } from 'app/shared/model/macroproceso.model';

@Component({
  selector: 'jhi-macroproceso-detail',
  templateUrl: './macroproceso-detail.component.html',
})
export class MacroprocesoDetailComponent implements OnInit {
  macroproceso: IMacroproceso | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ macroproceso }) => (this.macroproceso = macroproceso));
  }

  previousState(): void {
    window.history.back();
  }
}
