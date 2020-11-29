import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISubProceso } from 'app/shared/model/sub-proceso.model';

@Component({
  selector: 'jhi-sub-proceso-detail',
  templateUrl: './sub-proceso-detail.component.html',
})
export class SubProcesoDetailComponent implements OnInit {
  subProceso: ISubProceso | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ subProceso }) => (this.subProceso = subProceso));
  }

  previousState(): void {
    window.history.back();
  }
}
