import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITecnicasEvaluativas } from 'app/shared/model/tecnicas-evaluativas.model';

@Component({
  selector: 'jhi-tecnicas-evaluativas-detail',
  templateUrl: './tecnicas-evaluativas-detail.component.html',
})
export class TecnicasEvaluativasDetailComponent implements OnInit {
  tecnicasEvaluativas: ITecnicasEvaluativas | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tecnicasEvaluativas }) => (this.tecnicasEvaluativas = tecnicasEvaluativas));
  }

  previousState(): void {
    window.history.back();
  }
}
