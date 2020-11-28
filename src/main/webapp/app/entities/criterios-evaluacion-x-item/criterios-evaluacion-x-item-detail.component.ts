import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICriteriosEvaluacionXItem } from 'app/shared/model/criterios-evaluacion-x-item.model';

@Component({
  selector: 'jhi-criterios-evaluacion-x-item-detail',
  templateUrl: './criterios-evaluacion-x-item-detail.component.html',
})
export class CriteriosEvaluacionXItemDetailComponent implements OnInit {
  criteriosEvaluacionXItem: ICriteriosEvaluacionXItem | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ criteriosEvaluacionXItem }) => (this.criteriosEvaluacionXItem = criteriosEvaluacionXItem));
  }

  previousState(): void {
    window.history.back();
  }
}
