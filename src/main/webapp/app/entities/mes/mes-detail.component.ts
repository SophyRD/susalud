import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMes } from 'app/shared/model/mes.model';

@Component({
  selector: 'jhi-mes-detail',
  templateUrl: './mes-detail.component.html',
})
export class MesDetailComponent implements OnInit {
  mes: IMes | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ mes }) => (this.mes = mes));
  }

  previousState(): void {
    window.history.back();
  }
}
