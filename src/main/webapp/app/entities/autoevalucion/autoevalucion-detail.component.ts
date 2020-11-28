import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IAutoevalucion } from 'app/shared/model/autoevalucion.model';

@Component({
  selector: 'jhi-autoevalucion-detail',
  templateUrl: './autoevalucion-detail.component.html',
})
export class AutoevalucionDetailComponent implements OnInit {
  autoevalucion: IAutoevalucion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ autoevalucion }) => (this.autoevalucion = autoevalucion));
  }

  previousState(): void {
    window.history.back();
  }
}
