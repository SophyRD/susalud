import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubproceso } from 'app/shared/model/subproceso.model';

@Component({
  selector: 'jhi-subproceso-detail',
  templateUrl: './subproceso-detail.component.html',
})
export class SubprocesoDetailComponent implements OnInit {
  subproceso: ISubproceso | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subproceso }) => (this.subproceso = subproceso));
  }

  previousState(): void {
    window.history.back();
  }
}
