import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITecnicasEvaluativasXItem } from 'app/shared/model/tecnicas-evaluativas-x-item.model';

@Component({
  selector: 'jhi-tecnicas-evaluativas-x-item-detail',
  templateUrl: './tecnicas-evaluativas-x-item-detail.component.html',
})
export class TecnicasEvaluativasXItemDetailComponent implements OnInit {
  tecnicasEvaluativasXItem: ITecnicasEvaluativasXItem | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tecnicasEvaluativasXItem }) => (this.tecnicasEvaluativasXItem = tecnicasEvaluativasXItem));
  }

  previousState(): void {
    window.history.back();
  }
}
