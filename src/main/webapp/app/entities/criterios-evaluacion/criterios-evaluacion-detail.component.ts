import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICriteriosEvaluacion } from 'app/shared/model/criterios-evaluacion.model';

@Component({
  selector: 'jhi-criterios-evaluacion-detail',
  templateUrl: './criterios-evaluacion-detail.component.html',
})
export class CriteriosEvaluacionDetailComponent implements OnInit {
  criteriosEvaluacion: ICriteriosEvaluacion | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ criteriosEvaluacion }) => (this.criteriosEvaluacion = criteriosEvaluacion));
  }

  previousState(): void {
    window.history.back();
  }
}
