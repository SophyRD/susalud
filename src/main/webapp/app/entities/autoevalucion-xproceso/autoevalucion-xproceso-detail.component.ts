import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAutoevalucionXproceso } from 'app/shared/model/autoevalucion-xproceso.model';

@Component({
  selector: 'jhi-autoevalucion-xproceso-detail',
  templateUrl: './autoevalucion-xproceso-detail.component.html',
})
export class AutoevalucionXprocesoDetailComponent implements OnInit {
  autoevalucionXproceso: IAutoevalucionXproceso | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autoevalucionXproceso }) => (this.autoevalucionXproceso = autoevalucionXproceso));
  }

  previousState(): void {
    window.history.back();
  }
}
